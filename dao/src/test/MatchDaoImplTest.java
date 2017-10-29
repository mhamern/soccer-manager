import cz.muni.fi.pa165.soccermanager.PersistentContext;
import cz.muni.fi.pa165.soccermanager.dao.MatchDao;
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
 * @author 445720 Martin Hamernik
 * @version 10/26/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistentContext.class)
@Transactional
public class MatchDaoImplTest {

    @Autowired
    private MatchDao matchDao;

    @PersistenceContext
    private EntityManager manager;


    @Before
    public void setUp() {

    }

    @Test
    public void testInsertMatch() {
        Match inserted = getTestMatchOne();
        matchDao.insert(inserted);

        assertEquals("Match retrieved from DAO does not equal expected result",
        inserted, manager.find(Match.class, inserted.getId()));
    }

    @Test
    public void insertTwoMatches() {
        Match insertedOne = getTestMatchOne();
        Match insertedTwo = getTestMatchTwo();
        matchDao.insert(insertedOne);
        matchDao.insert(insertedTwo);

        assertEquals("First match retrieved from DAO does not equal to expected result",
                insertedOne, manager.find(Match.class, insertedOne.getId()));

        assertEquals("Second match retrieved from DAO does not equal expected result",
                insertedTwo, manager.find(Match.class, insertedTwo.getId()));
    }

    @Test
    public void updateMatch() {
        Match inserted = getTestMatchOne();
        manager.persist(inserted);

        Match updated = getTestMatchOne();
        updated.setId(manager.find(Match.class, inserted.getId()).getId());
        updated.setStadium("Eden");
        matchDao.update(updated);

        assertEquals("Match retrieved from DAO does not equal updated match",
                updated, manager.find(Match.class, inserted.getId()));

    }

    @Test
    public void updateTwoMatches() {
        Match insertedOne = getTestMatchOne();
        Match insertedTwo = getTestMatchTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        Match updatedOne = getTestMatchOne();
        updatedOne.setId(manager.find(Match.class, insertedOne.getId()).getId());
        updatedOne.setStadium("San Siro");
        matchDao.update(updatedOne);

        assertEquals("Match retrieved from DAO does not equal to first updated match",
                updatedOne, manager.find(Match.class, insertedOne.getId()));

        assertEquals("Match retrieved from DAO does not equal to match that was not updated",
                insertedTwo, manager.find(Match.class, insertedTwo.getId()));

        Match updatedTwo = getTestMatchTwo();
        updatedTwo.setId(manager.find(Match.class, insertedTwo.getId()).getId());
        updatedTwo.setStadium("Anfield Road");
        matchDao.update(updatedTwo);

        assertEquals("Match retrieved from DAO does not equal to second updated match",
                updatedTwo, manager.find(Match.class, insertedTwo.getId()));

        assertEquals("Match retrieved from DAO does not equal to match that was not updated",
                updatedOne, manager.find(Match.class, updatedOne.getId()));
    }

    @Test
    public void deleteMatch() {
        Match inserted = getTestMatchOne();
        manager.persist(inserted);
        matchDao.delete(inserted.getId());

        assertNull("Entity was not deleted",
                manager.find(Match.class, inserted.getId()));
    }

    @Test
    public void deleteTwoMatches() {
        Match insertedOne = getTestMatchOne();
        Match insertedTwo = getTestMatchTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        matchDao.delete(insertedOne.getId());

        assertNull("First match was not deleted properly",
                manager.find(Match.class, insertedOne.getId()));
        assertEquals("Match retrieved from DAO does not equal to first match that was not deleted",
                insertedTwo, manager.find(Match.class, insertedTwo.getId()));

        matchDao.delete(insertedTwo.getId());

        assertNull("Second match was not deleted properly",
                manager.find(Match.class, insertedTwo.getId()));
    }

    @Test
    public void fetchByIdMatch() {
        Match inserted = getTestMatchOne();
        manager.persist(inserted);

        assertEquals("Match retrieved from DAO does not equal expected result",
                inserted, matchDao.fetchById(inserted.getId()));
    }

    @Test
    public void fetchByIdTwoMatches() {
        Match insertedOne = getTestMatchOne();
        Match insertedTwo = getTestMatchTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        assertEquals("Match retrieved from DAO does not equal to first match",
                insertedOne, matchDao.fetchById(insertedOne.getId()));

        assertEquals("Match retrieved from DAO does not equal to second match",
                insertedTwo, matchDao.fetchById(insertedTwo.getId()));
    }

    @Test
    public void fetchByIdAllMatch() {
        Match inserted = getTestMatchOne();
        manager.persist(inserted);

        List<Match> matches = matchDao.fetchAll();

        assertTrue("Length of list retrieved from DAO does not equal 1",
                matches != null && matches.size() == 1);

        assertEquals("Match in list does not equal to match inserted to DB",
                inserted, matches.get(0));
    }

    @Test
    public void fetchByIdAllTwoMatches() {
        Match insertedOne = getTestMatchOne();
        Match insertedTwo = getTestMatchTwo();
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        List<Match> matches = matchDao.fetchAll();

        assertTrue("Length of list retrieved from DAO does not equal 2",
                matches != null && matches.size() == 2);

        assertTrue("List does not contain first match",
                matches.contains(insertedOne));

        assertTrue("List does not contain second match",
                matches.contains(insertedTwo));
    }

    private Match getTestMatchOne() {
        return new Match.MatchBuilder(
                new Team(),
                new Team(),
                new Date())
                .stadium("Santiago Bernabeu")
                .build();
    }

    private Match getTestMatchTwo() {
        return new Match.MatchBuilder(
                new Team(),
                new Team(),
                new Date())
                .stadium("Stamford Bridge")
                .build();
    }

}
