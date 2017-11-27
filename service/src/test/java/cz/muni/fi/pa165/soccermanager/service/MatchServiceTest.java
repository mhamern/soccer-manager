package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dao.MatchDao;
import cz.muni.fi.pa165.soccermanager.dao.PlayerDao;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.soccermanager.service.exceptions.SoccerManagerServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * class contains tests of match service
 * @author 456519 Filip Lux
 * @version 11/24/2017
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class MatchServiceTest {

    private MatchDao matchDao = mock(MatchDao.class);

    @Autowired
    @InjectMocks
    private MatchService matchService;

    private Match match12;
    private Match match34;
    private Match match21;
    private Match match13;
    private Match matchUncorrect;

    private Team team1;
    private Team team2;
    private Team team3;
    private Team team4;
    private Team uninitializedTeam;

    private League league;

    private LocalDate futureDate;
    private LocalDate pastDate;

    @Before
    public void setup() {
        matchService = new MatchServiceImpl(matchDao);

        pastDate = LocalDate.now().minusMonths(1);
        futureDate = LocalDate.now().plusMonths(3);

        league = new League.LeagueBuilder(
                "Premier League",
                NationalityEnum.England
        ).build();

        team1 = new Team.TeamBuilder(
                "Paris Saint Germain",
                NationalityEnum.France,
                StadiumEnum.Parc_des_Princes,
                league
        ).build();

        team2 = new Team.TeamBuilder(
                "Manchester United",
                NationalityEnum.England,
                StadiumEnum.Old_Trafford,
                league
        ).build();

        team3 = new Team.TeamBuilder(
                "Zbrojovka Brno",
                NationalityEnum.CzechRepublic,
                StadiumEnum.Za_Luzankami,
                league
        ).build();

        team4 = new Team.TeamBuilder(
                "FC Barcelona",
                NationalityEnum.Spain,
                StadiumEnum.Camp_Nou,
                league
        ).build();

        match12 = new Match.MatchBuilder(
                team1,
                team2,
                futureDate
        ).build();

        match21 = new Match.MatchBuilder(
                team2,
                team1,
                futureDate
        ).build();

        match34 = new Match.MatchBuilder(
                team3,
                team4,
                futureDate
        ).build();

        match13 = new Match.MatchBuilder(
                team1,
                team3,
                futureDate.plusDays(3)
        ).build();
    }

    @Test
    public void fetchByIdMatch() {
        when(matchDao.fetchById(1)).thenReturn(match12);

        Match found = matchService.fetchById(1);

        assertEquals(match12, found,
                "There was found correct match");
    }

    @Test
    public void fetchByIdTeamNotFound() {
        when(matchDao.fetchById(1)).thenReturn(null);

        Match found = matchService.fetchById(1);

        assertNull(found, "Match retrieved by service should be null.");
    }

    @Test
    public void fetchAllMatches() {
        List<Match> matches = new ArrayList<>();
        matches.add(match12);
        matches.add(match34);

        when(matchDao.fetchAll()).thenReturn(matches);

        List<Match> result = matchService.fetchAll();

        assertTrue(result.size() == 2,
                "There should be two matches in the list");
        assertTrue(result.contains(match12),
                "List should contain match12");
        assertTrue(result.contains(match34),
                "List should contain match34");
        assertTrue(!result.contains(match13),
                "List should not contain match13");
    }

    @Test
    public void fetchAllMatchesEmpty() {
        List<Match> matches = new ArrayList<>();

        when(matchDao.fetchAll()).thenReturn(matches);

        List<Match> result = matchService.fetchAll();

        assertTrue(result.isEmpty(),
                "List should be empty");
    }


    @Test (expected = SoccerManagerServiceException.class)
    public void testCreateMatchSameTeams() {

        matchUncorrect = new Match.MatchBuilder(
                team1,
                team1,
                futureDate
        ).build();

        matchService.createMatch(matchUncorrect);
    }

    @Test
    public void createMatch() {

        List<Match> matches = new ArrayList<>();
        matches.add(match12);
        matches.add(match34);

        doNothing().when(matchDao).insert(match13);
        when(matchDao.fetchAll()).thenReturn(matches);

        matchService.createMatch(match13);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTeamNull() {
        List<Match> matches = new ArrayList<>();
        matches.add(match12);
        matches.add(match34);

        when(matchDao.fetchAll()).thenReturn(matches);
        doNothing().when(matchDao).insert(null);

        matchService.createMatch(null);
    }

    @Test (expected = SoccerManagerServiceException.class)
    public void testCreateMatchPastDate() {

        matchUncorrect = new Match.MatchBuilder(
                team1,
                team2,
                pastDate
        ).build();

        matchService.createMatch(matchUncorrect);

    }

    @Test
    public void updateMatch() {

        Match match12up = new Match.MatchBuilder(
                match12.getHomeTeam(),
                match12.getAwayTeam(),
                match12.getDate()
        ).build();

        when(matchDao.fetchAll()).thenReturn(Collections.singletonList(match12up));
        doNothing().when(matchDao).update(match12up);

        match12up.setId(match12.getId());
        match12up.setDate(futureDate.plusWeeks(3));
        matchService.updateMatch(match12up);
    }

    @Test(expected = SoccerManagerServiceException.class)
    public void updateNotExistingMatch() {

        when(matchDao.fetchAll()).thenReturn(Collections.singletonList(match13));
        doNothing().when(matchDao).update(match12);

        matchService.updateMatch(match12);
    }

    @Test
    public void fetchByDateEmpty() {
        when(matchDao.fetchByDate(futureDate)).thenReturn(Collections.EMPTY_LIST);

        List<Match> matches = matchService.fetchByDate(futureDate);

        assertTrue(matches.size() == 0,
                "List should be empty");
    }

    @Test
    public void fetchByDate() {
        List<Match> matches = new ArrayList<>();
        matches.add(match12);
        matches.add(match34);

        when(matchDao.fetchByDate(futureDate)).thenReturn(matches);

        List<Match> result = matchService.fetchByDate(futureDate);

        assertTrue(result.size() == 2,
                "There should be two matches in the list");
        assertTrue(result.contains(match12),
                "List should contain match12");
        assertTrue(result.contains(match34),
                "List should contain match34");
        assertTrue(!result.contains(match13),
                "List should not contain match13");
    }

    @Test
    public void fetchByStaduim() {
        List<Match> matches = new ArrayList<>();
        matches.add(match12);
        matches.add(match13);

        StadiumEnum stadium = match12.getHomeTeam().getStadium();

        when(matchDao.fetchByStadium(stadium)).thenReturn(matches);

        List<Match> result = matchService.fetchByStadium(stadium);

        assertTrue(result.size() == 2,
                "There should be two matches in the list");
        assertTrue(result.contains(match12),
                "List should contain match12");
        assertTrue(result.contains(match13),
                "List should contain match34");
        assertTrue(!result.contains(match34),
                "List should not contain match13");
    }

    @Test
    public void isFinished() {
        List<Match> matches = new ArrayList<>();
        matches.add(match12);
        matches.add(match13);

        when(matchDao.fetchAll()).thenReturn(matches);
        when(matchDao.isFinished(match12)).thenReturn(true);

        Boolean finished = matchService.isFinished(match12);

        assertTrue(finished,
                "This match should be finished.");
    }

    @Test
    public void deleteMatch() {
        doNothing().when(matchDao).delete(10L);

        matchService.deleteMatch(10L);
    }

}
