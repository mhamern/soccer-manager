package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dao.LeagueDao;
import cz.muni.fi.pa165.soccermanager.dao.MatchDao;
import cz.muni.fi.pa165.soccermanager.dao.PlayerDao;
import cz.muni.fi.pa165.soccermanager.dao.TeamDao;
import cz.muni.fi.pa165.soccermanager.entity.*;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.service.exceptions.SoccerManagerServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author 476368 Iman Mehmandoust
 * @version 11/26/2017.
 */


public class LeagueServiceTest {

    private LeagueDao leagueDao = mock(LeagueDao.class);

    private TeamDao teamDao = mock(TeamDao.class);

    private PlayerDao playerDao = mock(PlayerDao.class);

    private MatchDao matchDao = mock(MatchDao.class);

    @Autowired
    @InjectMocks
    private LeagueService leagueService;
    private TeamService teamService;
    private League league1;
    private League league2;

    private Match match1;
    private Match match2;
    private Date date;

    private List<Match> matches;

    private Team team1;
    private Team team2;
    private Team team3;
    private Team team4;

    LocalDate futureDate;

    @Before
    public void setup() {


        leagueService = new LeagueServiceImpl(teamService, leagueDao, teamDao ,matchDao);

        futureDate = LocalDate.now().plusMonths(1);

        league1 = new League.LeagueBuilder(
                "Premier League",
                NationalityEnum.England
        ).build();

        team1 = new Team.TeamBuilder(
                "Real Madrid",
                NationalityEnum.Spain,
                StadiumEnum.Millennium_Stadium,
                league1
        ).build();

        team2 = new Team.TeamBuilder(
                "Chelsea FC",
                NationalityEnum.England,
                StadiumEnum.Stamford_Bridge,
                league1
        ).build();



        league2 = new League.LeagueBuilder(
                "Super League",
                NationalityEnum.Spain
        ).build();

        team3 = new Team.TeamBuilder(
                "FC Barcelona",
                NationalityEnum.Spain,
                StadiumEnum.Millennium_Stadium,
                league2
        ).build();

        team4 = new Team.TeamBuilder(
                "Manchester United",
                NationalityEnum.England,
                StadiumEnum.Old_Trafford,
                league2
        ).build();



        match1 = new Match.MatchBuilder(team1,team2,futureDate).build();
        match2 = new Match.MatchBuilder(team3,team4, futureDate).build();


        league1.setMatches(matches);
        league2.setMatches(matches);


    }


    @Test
    public void fetchByIdLeagueS() {
        when(leagueDao.fetchById(1)).thenReturn(league1);

        League found = leagueService.fetchById(1);

        assertEquals(league1, found,
                "League retrieved by service does not equal first League");
        assertNotEquals(league2, found,
                "League retrieved by service should not equal to second League");
    }


    @Test
    public void fetchByIdLeagueNotFoundS() {
        when(leagueDao.fetchById(1)).thenReturn(null);

        assertNull(leagueService.fetchById(1),
                "League retrieved by service should be null");
    }


    @Test
    public void fetchAllLeaguesS() {
        when(leagueDao.fetchAll()).thenReturn(Collections.singletonList(league1));

        List<League> actualLeagues = leagueService.fetchAll();

        assertTrue(actualLeagues.size() == 1,
                "There should be exactly one league in the list");
        assertTrue(actualLeagues.contains(league1),
                "Leagues retrieved by service does not contain first league");
        assertTrue(!actualLeagues.contains(league2),
                "Leagues retrieved by service should not contain second league");
    }


    @Test
    public void fetchAllLeaguesEmptyS() {
        when(leagueDao.fetchAll()).thenReturn(Collections.EMPTY_LIST);

        List<League> actualLeagues = leagueService.fetchAll();

        assertTrue(actualLeagues.isEmpty(),
                "There should be no leagues in the list");
    }


    @Test
    public void fetchLeagueByNameS() {
        when(leagueDao.fetchByName("Bundesliga")).thenReturn(league2);

        League found = leagueService.fetchByName("Bundesliga");

        assertNotEquals(league1, found,
                "League retrieved by service should not equal first league");
        assertEquals(league2, found,
                "League retrieved by service does not equal to second league");
    }


    @Test
    public void fetchLeagueByNameNotFoundS() {
        when(leagueDao.fetchByName("La Liga")).thenReturn(null);

        League found = leagueService.fetchByName("La Liga");

        assertNull(found, "League should be null");
    }



    @Test
    public void createLeagueS() {
        List<League> leagues = new ArrayList<>();
        leagues.add(league1);
        leagues.add(league2);

        League toCreate = new League.LeagueBuilder(
                "Championship",
                NationalityEnum.Ukraine
        ).build();

        when(leagueDao.fetchAll()).thenReturn(leagues);
        doNothing().when(leagueDao).insert(toCreate);

        leagueService.insert(toCreate);
    }



    @Test
    public void updateLeagueS() {
        List<League> leagues = new ArrayList<>();
        leagues.add(league1);
        leagues.add(league2);
        League toUpdate = new League.LeagueBuilder(
                league2.getName(),
                league2.getCountry()
        )
                .build();

        when(leagueDao.fetchAll()).thenReturn(leagues);
        doNothing().when(leagueDao).update(toUpdate);

        toUpdate.setId(league2.getId());
        toUpdate.setName("Bundesliga");
        leagueService.update(toUpdate);
    }


    @Test
    public void deleteLeagueS() {
        doNothing().when(leagueDao).delete(10L);

        leagueService.delete(10L);
    }




}