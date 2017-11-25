package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dao.MatchDao;
import cz.muni.fi.pa165.soccermanager.dao.PlayerDao;
import cz.muni.fi.pa165.soccermanager.dao.TeamDao;
import cz.muni.fi.pa165.soccermanager.entity.*;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
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
import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/25/2017.
 */

@ContextConfiguration(classes= ServiceConfiguration.class)
public class TeamServiceTest {

    private TeamDao teamDao = mock(TeamDao.class);

    private PlayerDao playerDao = mock(PlayerDao.class);

    private MatchDao matchDao = mock(MatchDao.class);

    @Autowired
    @InjectMocks
    private TeamService teamService;

    private Team team1;
    private Team team2;
    private League league1;
    private League league2;
    private Player player1;
    private Player player2;

    @Before
    public void setup() {
        teamService = new TeamServiceImpl(teamDao, playerDao, matchDao);

        player1 = new Player.PlayerBuilder(
                "Kylian Mbappe",
                PositionEnum.ATTACKER,
                NationalityEnum.France,
                LocalDate.of(1997, 10, 20))
                .build();

        player2 = new Player.PlayerBuilder(
                "Paul Pogba",
                PositionEnum.MIDFIELDER,
                NationalityEnum.France,
                LocalDate.of(1994, 10, 21))
                .build();

        league1 = new League.LeagueBuilder(
                "Ligue 1",
                NationalityEnum.France
        ).build();

        league2 = new League.LeagueBuilder(
                "Premier League",
                NationalityEnum.England
        ).build();

        team1 = new Team.TeamBuilder(
                "Paris Saint Germain",
                NationalityEnum.France,
                StadiumEnum.Parc_des_Princes,
                league1
        ).build();

        team2 = new Team.TeamBuilder(
                "Manchester United",
                NationalityEnum.England,
                StadiumEnum.Old_Trafford,
                league2
        ).build();

        team1.addPlayer(player1);
        team2.addPlayer(player2);
    }

    @Test
    public void fetchByIdTeam() {
        when(teamDao.fetchById(1)).thenReturn(team1);

        Team found = teamService.fetchById(1);

        assertEquals(team1, found,
                "Team retrieved by service does not equal first team");
        assertNotEquals(team2, found,
                "Team retrieved by service should not equal to second team");
    }

    @Test
    public void fetchByIdTeamNotFound() {
        when(teamDao.fetchById(1)).thenReturn(null);

        assertNull(teamService.fetchById(1),
                "Team retrieved by service should be null");
    }

    @Test
    public void fetchAllTeams() {
        when(teamDao.fetchAll()).thenReturn(Collections.singletonList(team1));

        List<Team> actualTeams = teamService.fetchAll();

        assertTrue(actualTeams.size() == 1,
                "There should be exactly one teams in the list");
        assertTrue(actualTeams.contains(team1),
                "Teams retrieved by service does not contain first team");
        assertTrue(!actualTeams.contains(team2),
                "Teams retrieved by service should not contain second team");
    }

    @Test
    public void fetchAllTeamsEmpty() {
        when(teamDao.fetchAll()).thenReturn(Collections.EMPTY_LIST);

        List<Team> actualTeams = teamService.fetchAll();

        assertTrue(actualTeams.isEmpty(),
                "There should be no teams in the list");
    }

    @Test
    public void fetchTeamByName() {
        when(teamDao.fetchByName("Manchester United")).thenReturn(team2);

        Team found = teamService.fetchByName("Manchester United");

        assertNotEquals(team1, found,
                "Team retrieved by service should not equal first team");
        assertEquals(team2, found,
                "Team retrieved by service does not equal to second team");
    }

    @Test
    public void fetchTeamByNameNotFound() {
        when(teamDao.fetchByName("Zbrojovka Brno")).thenReturn(null);

        Team found = teamService.fetchByName("Zbrojovka Brno");

        assertNull(found, "Team should be null");
    }

    @Test
    public void fetchTeamByManager() {
        Manager manager = new Manager.ManagerBuilder()
                .name("Jose Mourinho")
                .nationality(NationalityEnum.Portugal)
                .email("onlyone@gmail.com")
                .build();

        when(teamDao.fetchByManager(manager)).thenReturn(team2);

        Team found = teamService.fetchByManager(manager);

        assertNotEquals(team1, found,
                "Team retrieved by service should not equal first team");
        assertEquals(team2, found,
                "Team retrieved by service does not equal to second team");
    }

    @Test
    public void fetchTeamByManagerNotFound() {
        Manager manager = new Manager.ManagerBuilder()
                .name("Jose Mourinho")
                .nationality(NationalityEnum.Portugal)
                .email("onlyone@gmail.com")
                .build();

        when(teamDao.fetchByManager(manager)).thenReturn(null);

        Team found = teamService.fetchByManager(manager);

        assertNull(found, "Team should be null");
    }

    @Test
    public void fetchTeamsByOrigin() {
        when(teamDao.fetchByOrigin(NationalityEnum.England)).thenReturn(Collections.singletonList(team2));

        List<Team> found = teamService.fetchByOrigin(NationalityEnum.England);

        assertTrue(found.size() == 1,
                "There should be exactly one teams in the list");
        assertTrue(!found.contains(team1),
                "Teams retrieved by service should not contain first team");
        assertTrue(found.contains(team2),
                "Teams retrieved by service does not contain second team");
    }

    @Test
    public void fetchTeamsByOriginNotFound() {
        when(teamDao.fetchByOrigin(NationalityEnum.CzechRepublic)).thenReturn(Collections.EMPTY_LIST);

        List<Team> found = teamService.fetchByOrigin(NationalityEnum.CzechRepublic);

        assertTrue(found.size() == 0,
                "There should be no teams in the list");
    }

    @Test
    public void fetchTeamsByLeague() {
        when(teamDao.fetchByLeague(league2)).thenReturn(Collections.singletonList(team2));

        List<Team> found = teamService.fetchByLeague(league2);

        assertTrue(found.size() == 1,
                "There should be exactly one teams in the list");
        assertTrue(!found.contains(team1),
                "Teams retrieved by service should not contain first team");
        assertTrue(found.contains(team2),
                "Teams retrieved by service does not contain second team");
    }

    @Test
    public void fetchTeamsByLeagueNotFound() {
        League league3 = new League.LeagueBuilder(
                "Bundesliga",
                NationalityEnum.Germany
                )
                .build();

        when(teamDao.fetchByLeague(league3)).thenReturn(Collections.EMPTY_LIST);

        List<Team> found = teamService.fetchByLeague(league3);

        assertTrue(found.size() == 0,
                "There should be no teams in the list");
    }

    @Test
    public void fetchTeamsWithoutManager() {
        when(teamDao.fetchTeamsWithoutManager()).thenReturn(Collections.singletonList(team2));

        List<Team> found = teamService.fetchTeamsWithoutManager();

        assertTrue(found.size() == 1,
                "There should be exactly one teams in the list");
        assertTrue(!found.contains(team1),
                "Teams retrieved by service should not contain first team");
        assertTrue(found.contains(team2),
                "Teams retrieved by service does not contain second team");
    }

    @Test
    public void fetchTeamsWithoutManagerEmpty() {
        when(teamDao.fetchTeamsWithoutManager()).thenReturn(Collections.EMPTY_LIST);

        List<Team> found = teamService.fetchTeamsWithoutManager();

        assertTrue(found.size() == 0,
                "There should be no teams in the list");
    }

    @Test
    public void createTeam() {
        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        Team toCreate = new Team.TeamBuilder(
                "Arsenal FC",
                NationalityEnum.England,
                StadiumEnum.Emirates_Stadium,
                league2
                )
                .build();

        when(teamDao.fetchAll()).thenReturn(teams);
        doNothing().when(teamDao).insert(toCreate);

        teamService.create(toCreate);
    }

    @Test(expected = SoccerManagerServiceException.class)
    public void createTeamAlreadyCreated() {
        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        when(teamDao.fetchAll()).thenReturn(teams);
        doNothing().when(teamDao).insert(team2);

        teamService.create(team2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTeamNull() {
        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        when(teamDao.fetchAll()).thenReturn(teams);
        doNothing().when(teamDao).insert(team2);

        teamService.create(null);
    }

    @Test
    public void updateTeam() {
        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        Team toUpdate = new Team.TeamBuilder(
                team2.getName(),
                team2.getOrigin(),
                team2.getStadium(),
                team2.getLeague()
        )
                .build();

        when(teamDao.fetchAll()).thenReturn(teams);
        doNothing().when(teamDao).update(toUpdate);

        toUpdate.setId(team2.getId());
        toUpdate.setName("Manchester City");
        teamService.update(toUpdate);
    }

    @Test(expected = SoccerManagerServiceException.class)
    public void updateTeamAlreadyExists() {
        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        when(teamDao.fetchAll()).thenReturn(teams);
        doNothing().when(teamDao).update(team2);

        Team toUpdate = new Team.TeamBuilder(
                team2.getName(),
                team2.getOrigin(),
                team2.getStadium(),
                team2.getLeague()
                )
                .build();

        toUpdate.setId(58L);
        teamService.update(toUpdate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTeamNull() {
        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        when(teamDao.fetchAll()).thenReturn(teams);
        doNothing().when(teamDao).insert(null);

        teamService.update(null);
    }

    @Test
    public void deleteTeam() {
        doNothing().when(teamDao).delete(10L);

        teamService.delete(10L);
    }

    @Test
    public void addPlayerTeam() {
        Player toAdd = new Player.PlayerBuilder(
                "Cristiano Ronaldo",
                PositionEnum.ATTACKER,
                NationalityEnum.Portugal,
                LocalDate.of(1986, 12, 12)
        ).build();

        when(playerDao.fetchByTeam(team1)).thenReturn(Collections.singletonList(player1));
        when(playerDao.fetchFreeAgents()).thenReturn(Collections.singletonList(toAdd));
        doNothing().when(teamDao).update(team1);

        teamService.addPlayer(toAdd, team1);
    }

    @Test(expected = SoccerManagerServiceException.class)
    public void addPlayerAlreadyPlayingTeam() {

        when(playerDao.fetchByTeam(team1)).thenReturn(Collections.singletonList(player1));
        when(playerDao.fetchFreeAgents()).thenReturn(Collections.EMPTY_LIST);
        doNothing().when(teamDao).update(team1);

        teamService.addPlayer(player1, team1);
    }

    @Test(expected = SoccerManagerServiceException.class)
    public void addPlayerAlreadyPlayingForAnotherTeam() {

        when(playerDao.fetchByTeam(team1)).thenReturn(Collections.singletonList(player2));
        when(playerDao.fetchFreeAgents()).thenReturn(Collections.EMPTY_LIST);
        doNothing().when(teamDao).update(team1);

        teamService.addPlayer(player1, team1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPlayerNull() {
        when(playerDao.fetchByTeam(team1)).thenReturn(Collections.singletonList(player1));
        when(playerDao.fetchFreeAgents()).thenReturn(Collections.EMPTY_LIST);
        doNothing().when(teamDao).update(team1);

        teamService.addPlayer(null, team1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPlayerTeamNull() {
        when(playerDao.fetchByTeam(team1)).thenReturn(Collections.singletonList(player1));
        when(playerDao.fetchFreeAgents()).thenReturn(Collections.EMPTY_LIST);
        doNothing().when(teamDao).update(team1);

        teamService.addPlayer(player2, null);
    }

    @Test
    public void removePlayerPlayingForTeam() {

        when(playerDao.fetchByTeam(team1)).thenReturn(Collections.singletonList(player1));
        when(playerDao.fetchFreeAgents()).thenReturn(Collections.EMPTY_LIST);
        doNothing().when(teamDao).update(team1);

        teamService.removePlayer(player1, team1);
    }

    @Test(expected = SoccerManagerServiceException.class)
    public void removePlayerPlayingForAnotherTeam() {

        when(playerDao.fetchByTeam(team1)).thenReturn(Collections.singletonList(player2));
        when(playerDao.fetchFreeAgents()).thenReturn(Collections.EMPTY_LIST);
        doNothing().when(teamDao).update(team1);

        teamService.removePlayer(player1, team1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removePlayerNull() {
        when(playerDao.fetchByTeam(team1)).thenReturn(Collections.singletonList(player1));
        doNothing().when(teamDao).update(team1);

        teamService.removePlayer(null, team1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removePlayerTeamNull() {
        when(playerDao.fetchByTeam(team1)).thenReturn(Collections.singletonList(player1));
        doNothing().when(teamDao).update(team1);

        teamService.removePlayer(player2, null);
    }

    @Test
    public void assignManagerTeam() {
        Manager manager = new Manager.ManagerBuilder(
                "Jose Mourinho",
                NationalityEnum.Portugal,
                "onlyone@gmail.com")
                .build();

        when(teamDao.fetchByManager(manager)).thenReturn(null);
        when(teamDao.fetchTeamsWithoutManager()).thenReturn(Collections.singletonList(team1));
        doNothing().when(teamDao).update(team1);

        teamService.assignManager(manager, team1);
    }

    @Test(expected = SoccerManagerServiceException.class)
    public void assignManagerAlreadyManagingTeam() {
        Manager manager = new Manager.ManagerBuilder(
                "Jose Mourinho",
                NationalityEnum.Portugal,
                "onlyone@gmail.com")
                .build();

        when(teamDao.fetchByManager(manager)).thenReturn(team2);
        when(teamDao.fetchTeamsWithoutManager()).thenReturn(Collections.singletonList(team1));
        doNothing().when(teamDao).update(team1);

        teamService.assignManager(manager, team1);
    }

    @Test(expected = SoccerManagerServiceException.class)
    public void assignManagerToTeamAlreadyManagedByAnotherManager() {
        Manager manager = new Manager.ManagerBuilder(
                "Jose Mourinho",
                NationalityEnum.Portugal,
                "onlyone@gmail.com")
                .build();

        when(teamDao.fetchByManager(manager)).thenReturn(null);
        when(teamDao.fetchTeamsWithoutManager()).thenReturn(Collections.EMPTY_LIST);
        doNothing().when(teamDao).update(team1);

        teamService.assignManager(manager, team1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void assignManagerNull() {
        Manager manager = new Manager.ManagerBuilder(
                "Jose Mourinho",
                NationalityEnum.Portugal,
                "onlyone@gmail.com")
                .build();

        when(teamDao.fetchByManager(manager)).thenReturn(null);
        when(teamDao.fetchTeamsWithoutManager()).thenReturn(Collections.singletonList(team1));
        doNothing().when(teamDao).update(team1);

        teamService.assignManager(null, team1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void assignManagerTeamNull() {
        Manager manager = new Manager.ManagerBuilder(
                "Jose Mourinho",
                NationalityEnum.Portugal,
                "onlyone@gmail.com")
                .build();

        when(teamDao.fetchByManager(manager)).thenReturn(null);
        when(teamDao.fetchTeamsWithoutManager()).thenReturn(Collections.singletonList(team1));
        doNothing().when(teamDao).update(team1);

        teamService.assignManager(manager, null);
    }

    @Test
    public void removeManagerTeam() {

        doNothing().when(teamDao).update(team1);

        teamService.removeManager(team1);
    }


    @Test(expected = IllegalArgumentException.class)
    public void removeManagerTeamNull() {

        doNothing().when(teamDao).update(team1);

        teamService.removeManager(null);
    }

    @Test
    public void joinLeagueTeam() {
        Team newTeam = new Team.TeamBuilder(
                "Arsenal FC",
                NationalityEnum.England,
                StadiumEnum.Emirates_Stadium,
                league2
        )
                .build();

        newTeam.setLeague(null);
        doNothing().when(teamDao).update(team1);
        teamService.joinLeague(league2, newTeam);
    }

    @Test(expected = SoccerManagerServiceException.class)
    public void joinLeagueTeamAlreadyPlayingInDifferentLeague() {
        doNothing().when(teamDao).update(team1);
        teamService.joinLeague(league2, team1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void joinLeagueTeamNull() {
        doNothing().when(teamDao).update(team1);
        teamService.joinLeague(league2, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void joinLeagueTeamLeagueNull() {
        doNothing().when(teamDao).update(team1);
        teamService.joinLeague(null, team1);
    }

    @Test
    public void leaveLeagueTeam() {
        doNothing().when(teamDao).update(team1);
        teamService.leaveLeague(team1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void leaveLeagueTeamNull() {
        doNothing().when(teamDao).update(team1);
        teamService.leaveLeague(null);
    }

    @Test
    public void calculatePointsAndGoalsTeam() {
        Match match1 = new Match.MatchBuilder(team1, team2, LocalDate.of(2017, 10, 10)).build();
        match1.setFinished(false);

        Match match2 = new Match.MatchBuilder(team1, team2, LocalDate.of(2016, 10, 10)).build();
        match2.setFinished(true);
        match2.setHomeTeamGoals(2);
        match2.setAwayTeamGoals(1);

        Match match3 = new Match.MatchBuilder(team1, team2, LocalDate.of(2015, 10, 10)).build();
        match3.setFinished(true);
        match3.setHomeTeamGoals(5);
        match3.setAwayTeamGoals(1);

        Match match4 = new Match.MatchBuilder(team1, team2, LocalDate.of(2014, 10, 10)).build();
        match4.setFinished(true);
        match4.setHomeTeamGoals(1);
        match4.setAwayTeamGoals(1);

        Match match5 = new Match.MatchBuilder(team1, team2, LocalDate.of(2013, 10, 10)).build();
        match5.setFinished(true);
        match5.setHomeTeamGoals(0);
        match5.setAwayTeamGoals(3);

        List<Match> matches = new ArrayList<>();
        matches.add(match1);
        matches.add(match2);
        matches.add(match3);
        matches.add(match4);
        matches.add(match5);

        when(matchDao.fetchByTeam(team1)).thenReturn(matches);

        Team calculated = teamService.calculatePointsAndGoals(team1);

        assertEquals(team1, calculated,
                "Teams does not equal after calculation");
        assertTrue(calculated.getPoints() == 7,
                "First team should have 7 points");
        assertTrue(calculated.getGoalsScored() == 8,
                "First team should have 8 goals scored");
        assertTrue(calculated.getGoalsConceded() == 6,
                "First team should have 6 goals conceded");

    }

    @Test
    public void calculatePointsAndGoalsTeamNoMatchesPlayed() {
        when(matchDao.fetchByTeam(team1)).thenReturn(Collections.EMPTY_LIST);

        Team calculated = teamService.calculatePointsAndGoals(team1);

        assertEquals(team1, calculated,
                "Teams does not equal after calculation");
        assertTrue(calculated.getPoints() == 0,
                "First team should have 0 points");
        assertTrue(calculated.getGoalsScored() == 0,
                "First team should have 0 goals scored");
        assertTrue(calculated.getGoalsConceded() == 0,
                "First team should have 0 goals conceded");

    }

    @Test(expected = IllegalArgumentException.class)
    public void calculatePointsAndGoalsTeamNull() {
        when(matchDao.fetchByTeam(team1)).thenReturn(Collections.EMPTY_LIST);

        Team calculated = teamService.calculatePointsAndGoals(null);
    }





}
