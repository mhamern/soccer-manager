package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dao.LeagueDao;
import cz.muni.fi.pa165.soccermanager.dao.MatchDao;
import cz.muni.fi.pa165.soccermanager.dao.PlayerDao;
import cz.muni.fi.pa165.soccermanager.entity.*;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.soccermanager.service.exceptions.SoccerManagerServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

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

    private PlayerDao playerDao = mock(PlayerDao.class);

    private MatchDao matchDao = mock(MatchDao.class);

    @Autowired
    @InjectMocks
    private LeagueService leagueService;


    private League league1;
    private League league2;

    private Match match1;
    private Match match2;
    private Date date;

    private List<Match> matches;

    private Team team;

    @Before
    public void setup() {
        leagueService = new LeagueServiceImpl(team, team,LocalDate date);

        league1.setMatches("Real Madrid","Chelsea FC",LocalDate.of(2017, 11, 16));
        league2.setMatches("Manchester United","FC Barcelona",LocalDate.of(2016, 10, 21));


        league1.setMatches(matches);
        league2.setMatches(matches);

    }


    @Test
    public void fetchByIdLeague() {
        when(leagueDao.fetchById(1)).thenReturn(league1);

        League found = leagueService.fetchById(1);

        assertEquals(league1, found,
                "League retrieved by service does not equal first League");
        assertNotEquals(league2, found,
                "League retrieved by service should not equal to second League");
    }


    @Test
    public void fetchByIdLeagueNotFound() {
        when(leagueDao.fetchById(1)).thenReturn(null);

        assertNull(leagueService.fetchById(1),
                "League retrieved by service should be null");
    }


    @Test
    public void fetchAllLeagues() {
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
    public void fetchAllLeaguesEmpty() {
        when(leagueDao.fetchAll()).thenReturn(Collections.EMPTY_LIST);

        List<League> actualLeagues = leagueService.fetchAll();

        assertTrue(actualLeagues.isEmpty(),
                "There should be no leagues in the list");
    }


    @Test
    public void fetchLeagueByName() {
        when(leagueDao.fetchByName("Bundesliga")).thenReturn(league2);

        League found = leagueService.fetchByName("Bundesliga");

        assertNotEquals(league1, found,
                "League retrieved by service should not equal first league");
        assertEquals(league2, found,
                "League retrieved by service does not equal to second league");
    }


    @Test
    public void fetchLeagueByNameNotFound() {
        when(leagueDao.fetchByName("La Liga")).thenReturn(null);

        League found = leagueService.fetchByName("La Liga");

        assertNull(found, "League should be null");
    }



    @Test
    public void createLeague() {
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


    @Test(expected = SoccerManagerServiceException.class)
    public void createLeagueAlreadyCreated() {
        List<League> leagues = new ArrayList<>();
        leagues.add(league1);
        leagues.add(league2);

        when(leagueDao.fetchAll()).thenReturn(leagues);
        doNothing().when(leagueDao).insert(league2);

        leagueService.insert(league2);
    }


    @Test(expected = IllegalArgumentException.class)
    public void createLeagueNull() {
        List<League> leagues = new ArrayList<>();
        leagues.add(league1);
        leagues.add(league2);

        when(leagueDao.fetchAll()).thenReturn(leagues);
        doNothing().when(leagueDao).insert(league2);

        leagueService.insert(null);
    }


    @Test
    public void updateLeague() {
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


    @Test(expected = SoccerManagerServiceException.class)
    public void updateLeagueAlreadyExists() {
        List<League> leagues = new ArrayList<>();
        leagues.add(league1);
        leagues.add(league2);

        when(leagueDao.fetchAll()).thenReturn(leagues);
        doNothing().when(leagueDao).update(league2);

        League toUpdate = new League.LeagueBuilder(
                league2.getName(),
                league2.getCountry()

        )
                .build();

        toUpdate.setId(58L);
        leagueService.update(toUpdate);
    }


    @Test(expected = IllegalArgumentException.class)
    public void updateLeagueNull() {
        List<League> leagues = new ArrayList<>();
        leagues.add(league1);
        leagues.add(league2);

        when(leagueDao.fetchAll()).thenReturn(leagues);
        doNothing().when(leagueDao).insert(null);

        leagueService.update(null);
    }


    @Test
    public void deleteLeague() {
        doNothing().when(leagueDao).delete(10L);

        leagueService.delete(10L);
    }







}