package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.PersistentContext;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Manager;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;

import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import org.junit.Test;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistentContext.class)
@Transactional
public class TeamDaoImplTest {

    @Autowired
    private TeamDao dao;

    @PersistenceContext
    private EntityManager manager;

    @Test
    public void testInsertOne() {
        Team expected = fakeTeamOne();
        dao.insert(expected);
        assertEquals("Entity retrieved from PersistenceContext is not equal to entity sent to DAO",
                expected, manager.find(Team.class, expected.getId()));
    }

    @Test
    public void testInsertMore() {
        Team first = fakeTeamOne();
        dao.insert(first);
        Team second = fakeTeamTwo();
        dao.insert(second);

        assertEquals("Entity retrieved from PersistenceContext is not equal to entity sent to DAO",
                first, manager.find(Team.class, first.getId()));
        assertEquals("Entity retrieved from PersistenceContext is not equal to entity sent to DAO",
                second, manager.find(Team.class, second.getId()));
    }

    @Test
    public void testUpdateOne() {
        Team inserted = fakeTeamOne();
        manager.persist(inserted);

        Team updated = fakeTeamOne();
        updated.setId(inserted.getId());
        updated.setName("Banik");

        dao.update(updated);
        assertEquals("Entity retrieved from PersistenceContext is not equal to entity sent to DAO",
                updated, manager.find(Team.class, updated.getId()));
    }

    @Test
    public void testUpdateMore() {
        Team first = fakeTeamOne();
        manager.persist(first);
        Team second = fakeTeamTwo();
        manager.persist(second);

        Team firstUpdated = fakeTeamOne();
        firstUpdated.setId(first.getId());
        firstUpdated.setName("Banik");

        dao.update(firstUpdated);
        assertEquals("Entity retrieved from PersistenceContext is not equal to entity sent to DAO",
                firstUpdated, manager.find(Team.class, first.getId()));
        assertEquals("Entity retrieved from PersistenceContext is not equal to entity sent to DAO",
                second, manager.find(Team.class, second.getId()));

        Team secondUpdated = fakeTeamOne();
        secondUpdated.setId(second.getId());
        secondUpdated.setName("Zbrojovka");

        dao.update(secondUpdated);
        assertEquals("Entity retrieved from PersistenceContext is not equal to entity sent to DAO",
                firstUpdated, manager.find(Team.class, first.getId()));
        assertEquals("Entity retrieved from PersistenceContext is not equal to entity sent to DAO",
                secondUpdated, manager.find(Team.class, second.getId()));

        dao.update(first);
        assertEquals("Entity retrieved from PersistenceContext is not equal to entity sent to DAO",
                first, manager.find(Team.class, first.getId()));
        assertEquals("Entity retrieved from PersistenceContext is not equal to entity sent to DAO",
                secondUpdated, manager.find(Team.class, second.getId()));
    }

    @Test
    public void testDeleteOne() {
        Team inserted = fakeTeamOne();
        manager.persist(inserted);
        dao.delete(inserted.getId());
        assertNull("Entity should be null, team should be deleted", manager.find(Team.class, inserted.getId()));
    }

    @Test
    public void testDeleteMore() {
        Team first = fakeTeamOne();
        manager.persist(first);
        Team second = fakeTeamTwo();
        manager.persist(second);

        dao.delete(first.getId());
        assertNull("Entity should be null, team should be deleted", manager.find(Team.class, first.getId()));
        assertEquals("Entity should be in PersistenceContext",
                second, manager.find(Team.class, second.getId()));

        dao.delete(second.getId());
        assertNull("Entity should be null, team should be deleted", manager.find(Team.class, first.getId()));
        assertNull("Entity should be null, team should be deleted", manager.find(Team.class, second.getId()));
    }

    @Test
    public void testFetchByIdOne() {
        Team inserted = fakeTeamOne();
        manager.persist(inserted);
        assertEquals("Entity retrieved from Dao is not equal to entity sent to EntityManager",
                inserted, dao.fetchById(inserted.getId()));
    }

    @Test
    public void testFetchByIdOneNotFound() {
        Team inserted = fakeTeamOne();
        manager.persist(inserted);
        assertNull(dao.fetchById(10));
    }

    @Test
    public void testFetchByIdMore() {
        Team first = fakeTeamOne();
        manager.persist(first);
        Team second = fakeTeamTwo();
        manager.persist(second);

        assertEquals("Entity retrieved from Dao is not equal to entity sent to EntityManager",
                first, dao.fetchById(first.getId()));
        assertEquals("Entity retrieved from Dao is not equal to entity sent to EntityManager",
                second, dao.fetchById(second.getId()));
    }

    @Test
    public void testFetchAllOne() {
        Team inserted = fakeTeamOne();
        manager.persist(inserted);

        List<Team> all = dao.fetchAll();
        assertTrue("There should be only one team in DB", all != null && all.size() == 1);
        assertEquals("Entity retrieved by fetchAll is not equal to entity stored by EntityManager",
                inserted, all.get(0));
    }

    @Test
    public void testFetchAllMore() {
        Team first = fakeTeamOne();
        manager.persist(first);
        Team second = fakeTeamTwo();
        manager.persist(second);

        List<Team> all = dao.fetchAll();
        assertTrue("There should be exactly two teams in DB", all != null && all.size() == 2);
        assertTrue("First team not found", all.contains(first));
        assertTrue("Second team not found", all.contains(second));
    }

    @Test
    public void testFetchByName() {
        Team first = fakeTeamOne();
        manager.persist(first);
        Team second = fakeTeamTwo();
        manager.persist(second);

        Team found = dao.fetchByName("Zbrojovka Brno");
        assertTrue("Different team returned from DB", found != null && found.equals(first));
    }

    @Test
    public void testFetchByNameNotFound() {
        Team first = fakeTeamOne();
        manager.persist(first);
        Team second = fakeTeamTwo();
        manager.persist(second);

        Team found = dao.fetchByName("Real Madrid");
        assertNull(found);
    }

    @Test
    public void testFetchByOrigin() {
        Team first = fakeTeamOne();
        manager.persist(first);
        Team second = fakeTeamTwo();
        manager.persist(second);

        List<Team> found = dao.fetchByOrigin(NationalityEnum.CzechRepublic);
        assertTrue("There should be exactly two teams in DB", found != null && found.size() == 2);
        assertTrue("First team not found", found.contains(first));
        assertTrue("Second team not found", found.contains(second));
    }

    @Test
    public void testFetchByOriginNotFound() {
        Team first = fakeTeamOne();
        manager.persist(first);
        Team second = fakeTeamTwo();
        manager.persist(second);

        List<Team> found = dao.fetchByOrigin(NationalityEnum.England);
        assertTrue("There should be exactly zero teams in DB", found != null && found.size() == 0);
    }

    @Test
    public void testFetchByManager() {
        Team first = fakeTeamOne();
        Manager m = new Manager.ManagerBuilder(
                "Jose Mourinho",
                NationalityEnum.Portugal,
                "onlyone@gmail.com",
                false)
                .build();
        manager.persist(m);
        first.setManager(m);
        manager.persist(first);
        Team second = fakeTeamTwo();
        manager.persist(second);

        Team found = dao.fetchByManager(m);
        assertTrue("Different team returned from DB", found != null && found.equals(first));
    }

    @Test
    public void testFetchByManagerNotFound() {
        Team first = fakeTeamOne();
        Manager m = new Manager.ManagerBuilder(
                "Jose Mourinho",
                NationalityEnum.Portugal,
                "onlyone@gmail.com")
                .build();
        manager.persist(m);
        manager.persist(first);
        Team second = fakeTeamTwo();
        manager.persist(second);

        Team found = dao.fetchByManager(m);
        assertNull("Different team returned from DB", found);
    }

    @Test
    public void testFetchByLeague() {
        League league1 = new League.LeagueBuilder("Premier League", NationalityEnum.England).build();
        League league2 = new League.LeagueBuilder("Liga Santander", NationalityEnum.Spain).build();
        manager.persist(league1);
        manager.persist(league2);

        Team first = fakeTeamOne();
        first.setLeague(league1);
        manager.persist(first);

        Team second = fakeTeamTwo();
        second.setLeague(league2);
        manager.persist(second);



        List<Team> found = dao.fetchByLeague(league1);
        assertTrue("There should be exactly one team in DB", found != null && found.size() == 1);
        assertTrue("First team not found", found.contains(first));
        assertTrue("Second team should not be found", !found.contains(second));

        List<Team> foundSecond = dao.fetchByLeague(league2);
        assertTrue("There should be exactly one team in DB", foundSecond != null && foundSecond.size() == 1);
        assertTrue("First team should not be found", !foundSecond.contains(first));
        assertTrue("Second team not found", foundSecond.contains(second));
    }

    @Test
    public void testFetchTeamsWithoutManager() {
        Manager m = new Manager.ManagerBuilder(
                "Jose Mourinho",
                NationalityEnum.Portugal,
                "onlyone@gmail.com")
                .build();
        manager.persist(m);

        Team first = fakeTeamOne();
        manager.persist(first);
        Team second = fakeTeamTwo();
        second.setManager(m);
        manager.persist(second);


        List<Team> found = dao.fetchTeamsWithoutManager();
        assertTrue("There should be exactly one team in DB", found != null && found.size() == 1);
        assertTrue("First team not found", found.contains(first));
        assertTrue("Second team should not be found", !found.contains(second));
    }

    private Team fakeTeamOne() {
        Team team = new Team();
        team.setName("Zbrojovka Brno");
        team.setOrigin(NationalityEnum.CzechRepublic);
        team.setGoalsConceded(3);
        team.setGoalsScored(4);
        team.setPoints(12);
        team.setStadium(StadiumEnum.Za_Luzankami);
        return team;
    }

    private Team fakeTeamTwo() {
        Team team = new Team();
        team.setName("Sparta Praha");
        team.setOrigin(NationalityEnum.CzechRepublic);
        team.setGoalsConceded(10);
        team.setGoalsScored(0);
        team.setPoints(1);
        team.setStadium(StadiumEnum.Generali_Arena);
        return team;
    }

}
