import cz.muni.fi.pa165.soccermanager.PersistentContext;
import cz.muni.fi.pa165.soccermanager.dao.PlayerDao;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Match;

import cz.muni.fi.pa165.soccermanager.entity.Team;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
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
    public void updateTwoPlayeres() {
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

    private Player getTestPlayerOne() {
        return new Player.PlayerBuilder()
                .name("Cristiano Ronaldo")
                .position("Striker")
                .number(7)
                .born(new Date())
                .build();
    }

    private Player getTestPlayerTwo() {
        return new Player.PlayerBuilder()
                .name("Lio Messi")
                .position("Striker")
                .number(10)
                .born(new Date())
                .build();
    }

}