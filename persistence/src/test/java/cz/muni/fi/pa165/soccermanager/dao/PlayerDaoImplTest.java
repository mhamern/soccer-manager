package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.PersistentContext;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author 476368 Iman Mehmandoust
 * @version 10/29/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistentContext.class)
@Transactional
public class PlayerDaoImplTest {

    @Autowired
    private PlayerDao playerDao;

    @PersistenceContext
    private EntityManager manager;


    @Before
    public void setUp() {

    }

    @Test
    public void testInsertPlayer() {
        Player inserted = getTestPlayerOne();
        playerDao.insert(inserted);

        assertEquals("Player retrieved from DAO does not equal expected result",
                inserted, manager.find(Player.class, inserted.getId()));
    }

    @Test
    public void insertTwoPlayers() {
        Player insertedOne = getTestPlayerOne();
        Player insertedTwo = getTestPlayerTwo();
        playerDao.insert(insertedOne);
        playerDao.insert(insertedTwo);

        assertEquals("First Player retrieved from DAO does not equal to expected result",
                insertedOne, manager.find(Player.class, insertedOne.getId()));

        assertEquals("Second Player retrieved from DAO does not equal expected result",
                insertedTwo, manager.find(Player.class, insertedTwo.getId()));
    }

    @Test
    public void updatePlayer() {
        Player inserted = getTestPlayerOne();
        manager.persist(inserted);

        Player updated = getTestPlayerOne();
        updated.setId(manager.find(Player.class, inserted.getId()).getId());
        updated.setName("Nedved");
        playerDao.update(updated);

        assertEquals("Player retrieved from DAO does not equal updated player",
                updated, manager.find(Player.class, inserted.getId()));

    }

    @Test
    public void updateTwoPlayers() {
        Player insertedOne = getTestPlayerOne();
        Player insertedTwo = getTestPlayerTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        Player updatedOne = getTestPlayerOne();
        updatedOne.setId(manager.find(Player.class, insertedOne.getId()).getId());
        updatedOne.setName("Messi");
        playerDao.update(updatedOne);

        assertEquals("Player retrieved from DAO does not equal to first updated Player",
                updatedOne, manager.find(Player.class, insertedOne.getId()));

        assertEquals("Player retrieved from DAO does not equal to match that was not updated",
                insertedTwo, manager.find(Player.class, insertedTwo.getId()));

        Player updatedTwo = getTestPlayerTwo();
        updatedTwo.setId(manager.find(Player.class, insertedTwo.getId()).getId());
        updatedTwo.setName("Marek Suchý");
        playerDao.update(updatedTwo);

        assertEquals("Player retrieved from DAO does not equal to second updated Player",
                updatedTwo, manager.find(Player.class, insertedTwo.getId()));

        assertEquals("Player retrieved from DAO does not equal to Player that was not updated",
                updatedOne, manager.find(Player.class, updatedOne.getId()));
    }

    @Test
        public void deletePlayer() {
        Player inserted = getTestPlayerOne();
        manager.persist(inserted);
        playerDao.delete(inserted.getId());

        assertNull("Entity was not deleted",
                manager.find(Player.class, inserted.getId()));
    }

    @Test
    public void deleteTwoPlayers() {
        Player insertedOne = getTestPlayerOne();
        Player insertedTwo = getTestPlayerTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        playerDao.delete(insertedOne.getId());

        assertNull("First player was not deleted properly",
                manager.find(Match.class, insertedOne.getId()));
        assertEquals("Player retrieved from DAO does not equal to first player that was not deleted",
                insertedTwo, manager.find(Player.class, insertedTwo.getId()));

        playerDao.delete(insertedTwo.getId());

        assertNull("Second player was not deleted properly",
                manager.find(Player.class, insertedTwo.getId()));
    }

    @Test
    public void fetchByIdPlayer() {
        Player inserted = getTestPlayerOne();
        manager.persist(inserted);

        assertEquals("Player retrieved from DAO does not equal expected result",
                inserted, playerDao.fetchById(inserted.getId()));
    }

    @Test
    public void fetchByIdPlayerNotFound() {
        Player inserted = getTestPlayerOne();
        manager.persist(inserted);

        assertNull("Player should not be retrieved",
                playerDao.fetchById(11));
    }

    @Test
    public void fetchByIdTwoPlayers() {
        Player insertedOne = getTestPlayerOne();
        Player insertedTwo = getTestPlayerTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        assertEquals("Player retrieved from DAO does not equal to first player",
                insertedOne, playerDao.fetchById(insertedOne.getId()));

        assertEquals("Player retrieved from DAO does not equal to second player",
                insertedTwo, playerDao.fetchById(insertedTwo.getId()));
    }

    @Test
    public void fetchByIdAllPlayer() {
        Player inserted = getTestPlayerOne();
        manager.persist(inserted);

        List<Player> players = playerDao.fetchAll();

        assertTrue("Length of list retrieved from DAO does not equal 1",
                players != null && players.size() == 1);

        assertEquals("Player in list does not equal to player inserted to DB",
                inserted, players.get(0));
    }

    @Test
    public void fetchByIdAllTwoPlayers() {
        Player insertedOne = getTestPlayerOne();
        Player insertedTwo = getTestPlayerTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        List<Player> players = playerDao.fetchAll();

        assertTrue("Length of list retrieved from DAO does not equal 2",
                players != null && players.size() == 2);

        assertTrue("List does not contain first player",
                players.contains(insertedOne));

        assertTrue("List does not contain second player",
                players.contains(insertedTwo));
    }

    @Test
    public void fetchByTeam() {
        Player insertedOne = getTestPlayerOne();
        Player insertedTwo = getTestPlayerTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        League league = new League.LeagueBuilder("Premier League", NationalityEnum.England).build();
        manager.persist(league);

        Team team = new Team.TeamBuilder("Arsenal FC", NationalityEnum.England, league).build();
        team.addPlayer(insertedOne);
        team.addPlayer(insertedTwo);
        manager.persist(team);


        List<Player> players = playerDao.fetchByTeam(team);

        assertTrue("Length of list retrieved from DAO does not equal 2",
                players != null && players.size() == 2);

        assertTrue("List does not contain first player",
                players.contains(insertedOne));

        assertTrue("List does not contain second player",
                players.contains(insertedTwo));
    }

    @Test
    public void fetchByTeamNotFound() {
        Player insertedOne = getTestPlayerOne();
        Player insertedTwo = getTestPlayerTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        League league = new League.LeagueBuilder("Premier League", NationalityEnum.England).build();
        manager.persist(league);

        Team team = new Team.TeamBuilder("Arsenal FC", NationalityEnum.England, league).build();
        Team team2 = new Team.TeamBuilder("Chelsea FC", NationalityEnum.England, league).build();
        team.addPlayer(insertedOne);
        team.addPlayer(insertedTwo);
        manager.persist(team);


        List<Player> players = playerDao.fetchByTeam(team2);

        assertTrue("Length of list retrieved from DAO does not equal 0",
                players != null && players.size() == 0);
    }

    @Test
    public void fetchFreeAgents() {
        Player insertedOne = getTestPlayerOne();
        Player insertedTwo = getTestPlayerTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        League league = new League.LeagueBuilder("Premier League", NationalityEnum.England).build();
        manager.persist(league);

        Team team = new Team.TeamBuilder("Arsenal FC", NationalityEnum.England, league).build();
        team.addPlayer(insertedOne);
        manager.persist(team);


        List<Player> players = playerDao.fetchFreeAgents();

        assertTrue("Length of list retrieved from DAO does not equal 1",
                players != null && players.size() == 1);

        assertTrue("List should not contain first player",
                !players.contains(insertedOne));

        assertTrue("List does not contain second player",
                players.contains(insertedTwo));

    }

    @Test
    public void fetchFreeAgentsNotFound() {
        Player insertedOne = getTestPlayerOne();
        Player insertedTwo = getTestPlayerTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        League league = new League.LeagueBuilder("Premier League", NationalityEnum.England).build();
        manager.persist(league);

        Team team = new Team.TeamBuilder("Arsenal FC", NationalityEnum.England, league).build();
        team.addPlayer(insertedOne);
        team.addPlayer(insertedTwo);
        manager.persist(team);


        List<Player> players = playerDao.fetchFreeAgents();

        assertTrue("Length of list retrieved from DAO does not equal 0",
                players != null && players.size() == 0);

    }


    @Test
    public void fetchByNationality() {
        Player insertedOne = getTestPlayerOne();
        Player insertedTwo = getTestPlayerTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        List<Player> players = playerDao.fetchByNationality(NationalityEnum.Portugal);

        assertTrue("Length of list retrieved from DAO does not equal 1",
                players != null && players.size() == 1);

        assertTrue("List does not contain first player",
                players.contains(insertedOne));

        assertTrue("List should not contain second player",
                !players.contains(insertedTwo));
    }

    @Test
    public void fetchByNationalityNotFound() {
        Player insertedOne = getTestPlayerOne();
        Player insertedTwo = getTestPlayerTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        List<Player> players = playerDao.fetchByNationality(NationalityEnum.Cambodia);

        assertTrue("Length of list retrieved from DAO does not equal 0",
                players != null && players.size() == 0);
    }

    @Test
    public void fetchByPosition() {
        Player insertedOne = getTestPlayerOne();
        Player insertedTwo = getTestPlayerTwo();
        insertedTwo.setPosition(PositionEnum.MIDFIELDER);
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        List<Player> players = playerDao.fetchByPosition(PositionEnum.ATTACKER);

        assertTrue("Length of list retrieved from DAO does not equal 1",
                players != null && players.size() == 1);

        assertTrue("List does not contain first player",
                players.contains(insertedOne));

        assertTrue("List should not contain second player",
                !players.contains(insertedTwo));
    }

    @Test
    public void fetchByPositionNotFound() {
        Player insertedOne = getTestPlayerOne();
        Player insertedTwo = getTestPlayerTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        List<Player> players = playerDao.fetchByPosition(PositionEnum.DEFENDER);

        assertTrue("Length of list retrieved from DAO does not equal 0",
                players != null && players.size() == 0);
    }

    @Test
    public void fetchByName() {
        Player insertedOne = getTestPlayerOne();
        Player insertedTwo = getTestPlayerTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        assertEquals("Player retrieved from DAO does not equal to first player",
                insertedOne, playerDao.fetchByName("Cristiano Ronaldo"));

        assertEquals("Player retrieved from DAO does not equal to second player",
                insertedTwo, playerDao.fetchByName("Lio Messi"));
    }

    @Test
    public void fetchByNameNotFound() {
        Player insertedOne = getTestPlayerOne();
        Player insertedTwo = getTestPlayerTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        assertNull("Player should not be found",
                playerDao.fetchByName("Milan Baros"));
    }

    private Player getTestPlayerOne() {
        return new Player.PlayerBuilder(
                "Cristiano Ronaldo",
                PositionEnum.ATTACKER,
                NationalityEnum.Portugal,
                LocalDate.of(1980, 11, 1))
                .build();
    }

    private Player getTestPlayerTwo() {
        return new Player.PlayerBuilder(
                "Lio Messi",
                PositionEnum.ATTACKER,
                NationalityEnum.Argentina,
                LocalDate.of(1983, 10, 20))
                .build();
    }

}
