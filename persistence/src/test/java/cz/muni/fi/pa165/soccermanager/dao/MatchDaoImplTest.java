package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.PersistentContext;
import cz.muni.fi.pa165.soccermanager.dao.MatchDao;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Team;

import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.time.LocalDate;
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

    private Match match1;
    private Match match2;

    @Before
    public void setUp() {
        League league1 = new League.LeagueBuilder("La Liga", NationalityEnum.Spain).build();
        Team team1 = new Team.TeamBuilder("Real Madrid", NationalityEnum.Spain, StadiumEnum.San_Siro, league1).build();
        Team team2 = new Team.TeamBuilder("FC Barcelona", NationalityEnum.Spain, StadiumEnum.Camp_Nou, league1).build();

        League league2 = new League.LeagueBuilder("Premier League", NationalityEnum.England).build();
        Team team3 = new Team.TeamBuilder("Arsenal FC", NationalityEnum.England, StadiumEnum.Emirates_Stadium, league2).build();
        Team team4 = new Team.TeamBuilder("Chelsea FC", NationalityEnum.England, StadiumEnum.Stamford_Bridge, league2).build();
        manager.persist(league1);
        manager.persist(team1);
        manager.persist(team2);

        manager.persist(league2);
        manager.persist(team3);
        manager.persist(team4);

        match1 = new Match.MatchBuilder(
                team1,
                team2,
                LocalDate.now())
                .build();

        match2 = new Match.MatchBuilder(
                team1,
                team2,
                LocalDate.now())
                .build();
    }

    @Test
    public void testInsertMatch() {
        Match inserted = match1;
        matchDao.insert(inserted);

        assertEquals("Match retrieved from DAO does not equal expected result",
        inserted, manager.find(Match.class, inserted.getId()));
    }

    @Test
    public void insertTwoMatches() {
        Match insertedOne = match1;
        Match insertedTwo = match2;
        matchDao.insert(insertedOne);
        matchDao.insert(insertedTwo);

        assertEquals("First match retrieved from DAO does not equal to expected result",
                insertedOne, manager.find(Match.class, insertedOne.getId()));

        assertEquals("Second match retrieved from DAO does not equal expected result",
                insertedTwo, manager.find(Match.class, insertedTwo.getId()));
    }

    @Test
    public void updateMatch() {
        Match inserted = match1;
        manager.persist(inserted);

        Match updated = manager.find(Match.class, inserted.getId());
        updated.setStadium(StadiumEnum.Friends_Arena);
        matchDao.update(updated);

        assertEquals("Match retrieved from DAO does not equal updated match",
                updated, manager.find(Match.class, inserted.getId()));

    }

    @Test
    public void updateTwoMatches() {
        Match insertedOne = match1;
        Match insertedTwo = match2;
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        Match updatedOne =  manager.find(Match.class, insertedOne.getId());
        updatedOne.setStadium(StadiumEnum.London_Stadium);
        matchDao.update(updatedOne);

        assertEquals("Match retrieved from DAO does not equal to first updated match",
                updatedOne, manager.find(Match.class, insertedOne.getId()));

        assertEquals("Match retrieved from DAO does not equal to match that was not updated",
                insertedTwo, manager.find(Match.class, insertedTwo.getId()));

        Match updatedTwo = manager.find(Match.class, insertedTwo.getId());
        updatedTwo.setStadium(StadiumEnum.San_Siro);
        matchDao.update(updatedTwo);

        assertEquals("Match retrieved from DAO does not equal to second updated match",
                updatedTwo, manager.find(Match.class, insertedTwo.getId()));

        assertEquals("Match retrieved from DAO does not equal to match that was not updated",
                updatedOne, manager.find(Match.class, updatedOne.getId()));
    }

    @Test
    public void deleteMatch() {
        Match inserted = match1;
        manager.persist(inserted);
        matchDao.delete(inserted.getId());

        assertNull("Entity was not deleted",
                manager.find(Match.class, inserted.getId()));
    }

    @Test
    public void deleteTwoMatches() {
        Match insertedOne = match1;
        Match insertedTwo = match2;
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
        Match inserted = match1;
        manager.persist(inserted);

        assertEquals("Match retrieved from DAO does not equal expected result",
                inserted, matchDao.fetchById(inserted.getId()));
    }

    @Test
    public void fetchByIdTwoMatches() {
        Match insertedOne = match1;
        Match insertedTwo = match2;
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        assertEquals("Match retrieved from DAO does not equal to first match",
                insertedOne, matchDao.fetchById(insertedOne.getId()));

        assertEquals("Match retrieved from DAO does not equal to second match",
                insertedTwo, matchDao.fetchById(insertedTwo.getId()));
    }

    @Test
    public void fetchAllMatch() {
        Match inserted = match1;
        manager.persist(inserted);

        List<Match> matches = matchDao.fetchAll();

        assertTrue("Length of list retrieved from DAO does not equal 1",
                matches != null && matches.size() == 1);

        assertEquals("Match in list does not equal to match inserted to DB",
                inserted, matches.get(0));
    }

    @Test
    public void fetchAllTwoMatches() {
        Match insertedOne = match1;
        Match insertedTwo = match2;
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

}
