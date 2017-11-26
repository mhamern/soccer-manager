package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dao.PlayerDao;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.service.config.ServiceConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author 445720 Martin Hamernik
 * @version 11/25/2017.
 */

@ContextConfiguration(classes= ServiceConfiguration.class)
public class PlayerServiceTest {

    private PlayerDao playerDao = mock(PlayerDao.class);

    @Autowired
    @InjectMocks
    private PlayerService playerService;

    private Team team1;
    private Team team2;
    private League league1;
    private League league2;
    private Player player1;
    private Player player2;

    @Before
    public void setup() {
        playerService = new PlayerServiceImpl(playerDao);

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
        when(playerDao.fetchById(1)).thenReturn(player1);

        Player found = playerService.fetchById(1);

        assertEquals(player1, found,
                "Player retrieved by service does not equal first player");
        assertNotEquals(player2, found,
                "Player retrieved by service should not equal to second player");
    }

    @Test
    public void fetchByIdTeamNotFound() {
        when(playerDao.fetchById(1)).thenReturn(null);

        Player found = playerService.fetchById(1);

        assertNull(found,
                "Player retrieved by service should be null");
    }

    @Test
    public void fetchAllPlayers() {
        when(playerDao.fetchAll()).thenReturn(Collections.singletonList(player1));

        List<Player> players = playerService.fetchAll();

        assertTrue(players.size() == 1,
                "There should be exactly one player in the list");
        assertTrue(players.contains(player1),
                "List should contain first player");
        assertTrue(!players.contains(player2),
                "List should not contain second player");
    }

    @Test
    public void fetchAllPlayersEmpty() {
        when(playerDao.fetchAll()).thenReturn(Collections.EMPTY_LIST);

        List<Player> players = playerService.fetchAll();

        assertTrue(players.isEmpty(),
                "List should be empty");
    }

    @Test
    public void createPlayer() {
        Player toCreate = new Player.PlayerBuilder(
                "Alvaro Morata",
                PositionEnum.ATTACKER,
                NationalityEnum.Spain,
                LocalDate.of(1990,6,6)
                ).build();

        doNothing().when(playerDao).insert(toCreate);

        playerService.create(toCreate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createPlayerNull() {
        doNothing().when(playerDao).insert(null);

        playerService.create(null);
    }

    @Test
    public void updatePlayer() {
        Player toUpdate = new Player.PlayerBuilder(
                player1.getName(),
                player1.getPosition(),
                player1.getNationality(),
                player1.getBirthDate()
        ).build();

        doNothing().when(playerDao).insert(toUpdate);

        toUpdate.setId(player1.getId());
        toUpdate.setPosition(PositionEnum.DEFENDER);
        playerService.create(toUpdate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updatePlayerNull() {
        doNothing().when(playerDao).insert(null);

        playerService.create(null);
    }

    @Test
    public void deletePlayer() {
        doNothing().when(playerDao).delete(10L);

        playerService.delete(10L);
    }

    @Test
    public void fetchByTeamPlayer() {
        when(playerDao.fetchByTeam(team1)).thenReturn(Collections.singletonList(player1));

        List<Player> players = playerService.fetchByTeam(team1);

        assertTrue(players.size() == 1,
                "There should be exactly one player in the list");
        assertTrue(players.contains(player1),
                "List should contain first player");
        assertTrue(!players.contains(player2),
                "List should not contain second player");
    }

    @Test
    public void fetchByTeamPlayerEmpty() {
        when(playerDao.fetchByTeam(team1)).thenReturn(Collections.EMPTY_LIST);

        List<Player> players = playerService.fetchByTeam(team1);

        assertTrue(players.size() == 0,
                "List should be empty");
    }

    @Test
    public void fetchByPositionPlayer() {
        when(playerDao.fetchByPosition(PositionEnum.ATTACKER)).thenReturn(Collections.singletonList(player1));

        List<Player> players = playerService.fetchByPosition(PositionEnum.ATTACKER);

        assertTrue(players.size() == 1,
                "There should be exactly one player in the list");
        assertTrue(players.contains(player1),
                "List should contain first player");
        assertTrue(!players.contains(player2),
                "List should not contain second player");
    }

    @Test
    public void fetchByPositionPlayerEmpty() {
        when(playerDao.fetchByPosition(PositionEnum.DEFENDER)).thenReturn(Collections.EMPTY_LIST);

        List<Player> players = playerService.fetchByPosition(PositionEnum.DEFENDER);

        assertTrue(players.size() == 0,
                "List should be empty");
    }

    @Test
    public void fetchByNationalityPlayer() {
        when(playerDao.fetchByNationality(NationalityEnum.France)).thenReturn(Collections.singletonList(player1));

        List<Player> players = playerService.fetchByNationality(NationalityEnum.France);

        assertTrue(players.size() == 1,
                "There should be exactly one player in the list");
        assertTrue(players.contains(player1),
                "List should contain first player");
        assertTrue(!players.contains(player2),
                "List should not contain second player");
    }

    @Test
    public void fetchByNationalityPlayerEmpty() {
        when(playerDao.fetchByNationality(NationalityEnum.Estonia)).thenReturn(Collections.EMPTY_LIST);

        List<Player> players = playerService.fetchByNationality(NationalityEnum.Estonia);

        assertTrue(players.size() == 0,
                "List should be empty");
    }

    @Test
    public void fetchByNamePlayer() {
        when(playerDao.fetchByName("Kylian Mbappe")).thenReturn(player1);

        Player found = playerService.fetchByName("Kylian Mbappe");

        assertEquals(player1, found,
                "Player retrieved by service does not equal first player");
        assertNotEquals(player2, found,
                "Player retrieved by service should not equal to second player");
    }

    @Test
    public void fetchByNamePlayerNull() {
        when(playerDao.fetchByName("Fernando Torres")).thenReturn(null);

        Player found = playerService.fetchByName("Fernando Torres");

        assertNull(found,"Retrieved player should be null");
    }

    @Test
    public void fetchFreeAgents() {
        when(playerDao.fetchFreeAgents()).thenReturn(Collections.singletonList(player1));

        List<Player> players = playerService.fetchFreeAgents();

        assertTrue(players.size() == 1,
                "There should be exactly one player in the list");
        assertTrue(players.contains(player1),
                "List should contain first player");
        assertTrue(!players.contains(player2),
                "List should not contain second player");
    }

    @Test
    public void fetchFreeAgentsEmpty() {
        when(playerDao.fetchFreeAgents()).thenReturn(Collections.EMPTY_LIST);

        List<Player> players = playerService.fetchFreeAgents();

        assertTrue(players.size() == 0,
                "List should be empty");
    }
}
