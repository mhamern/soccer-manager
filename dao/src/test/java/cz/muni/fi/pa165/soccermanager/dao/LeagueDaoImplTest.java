package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.PersistentContext;
import cz.muni.fi.pa165.soccermanager.dao.LeagueDao;
import cz.muni.fi.pa165.soccermanager.entity.League;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author 456519 Filip Lux
 * @version 10/27/2017
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistentContext.class)
@Transactional
public class LeagueDaoImplTest {

    @Autowired
    private LeagueDao leagueDao;

    @PersistenceContext
    private EntityManager manager;

    @Before
    public void init() {

    }


    @Test
    public void fetchByIdLeague() {
        League testPremierLeague = getTestPremierLeague();
        manager.persist(testPremierLeague);

        assertEquals("League retrieved from DAO does not equal expected result",
                testPremierLeague, leagueDao.fetchById(testPremierLeague.getId()));

    }

    @Test
    public void fetchAllByIdLeague() {
        League testPremierLeague = getTestPremierLeague();
        League testBundesliga = getTestBundesliga();
        manager.persist(testPremierLeague);
        manager.persist(testBundesliga);

        List<League> leagues = leagueDao.fetchAll();


        assertTrue("Length of list retrieved from DAO does not equal 2",
                leagues != null && leagues.size() == 2);

        assertTrue("List does not contain testPremierLeague",
                leagues.contains(testPremierLeague));

        assertTrue("List does not contain testBundesliga",
                leagues.contains(testBundesliga));
    }

    @Test
    public void insertLeague() {
        League league = getTestPremierLeague();
        manager.persist(league);

        assertEquals("League was not inserted",
                league, manager.find(League.class, league.getId()));

    }

    @Test
    public void updateLeague() {
        League league = getTestPremierLeague();
        manager.persist(league);

        League updated_league = getTestPremierLeague();
        updated_league.setId(manager.find(League.class, league.getId()).getId());
        updated_league.setCountry("Czech");
        leagueDao.update(updated_league);

        assertEquals("League retrieved from DAO does not equal updated league",
                updated_league, manager.find(League.class, league.getId()));

        assertEquals("League attribute was not updated",
                updated_league.getCountry(), manager.find(League.class, league.getId()).getCountry());

    }

    @Test
    public void deleteMatch() {
        League league = getTestPremierLeague();
        manager.persist(league);
        leagueDao.delete(league.getId());

        assertNull("Entity was not deleted",
                manager.find(League.class, league.getId()));
    }


    private League getTestPremierLeague() {
        return new League.LeagueBuilder("Premier League", "England")
                .build();
    }

    private League getTestBundesliga() {
        return new League.LeagueBuilder("Bundesliga", "Germany")
                .build();
    }


}
