package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.PersistentContext;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
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
public class TeamDayImplTest {

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

    private Team fakeTeamOne() {
        Team team = new Team();
        team.setName("Zbrojovka Brno");
        team.setOrigin(NationalityEnum.CzechRepublic);
        team.setGoalsConceded(3);
        team.setGoalsScored(4);
        team.setPoints(12);
        return team;
    }

    private Team fakeTeamTwo() {
        Team team = new Team();
        team.setName("Sparta Praha");
        team.setOrigin(NationalityEnum.CzechRepublic);
        team.setGoalsConceded(10);
        team.setGoalsScored(0);
        team.setPoints(1);
        return team;
    }

}
