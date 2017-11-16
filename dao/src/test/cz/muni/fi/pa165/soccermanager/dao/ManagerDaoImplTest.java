package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.PersistentContext;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Manager;
import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Team;

import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
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
public class ManagerDaoImplTest {

    @Autowired
    private ManagerDao managerDao;

    @PersistenceContext
    private EntityManager entityManager;

    private Manager manager1;
    private Manager manager2;

    @Before
    public void setUp() {

        manager1 = new Manager.ManagerBuilder(
                "Jose Mourinho",
                NationalityEnum.Portugal,
                "thespecialone@gmail.com")
                .build();

        manager2 = new Manager.ManagerBuilder(
                "Pep Guardiola",
                NationalityEnum.Spain,
                "tikitaka@gmail.com")
                .build();
    }

    @Test
    public void testInsertManager() {
        Manager inserted = manager1;
        managerDao.insert(inserted);

        assertEquals("Manager retrieved from DAO does not equal expected result",
                inserted, entityManager.find(Manager.class, inserted.getId()));
    }

    @Test
    public void insertTwoManager() {
        Manager insertedOne = manager1;
        Manager insertedTwo = manager2;
        managerDao.insert(insertedOne);
        managerDao.insert(insertedTwo);

        assertEquals("First manager retrieved from DAO does not equal to expected result",
                insertedOne, entityManager.find(Manager.class, insertedOne.getId()));

        assertEquals("Second manager retrieved from DAO does not equal expected result",
                insertedTwo, entityManager.find(Manager.class, insertedTwo.getId()));
    }

    @Test
    public void updateManager() {
        Manager inserted = manager1;
        entityManager.persist(inserted);

        Manager updated = entityManager.find(Manager.class, inserted.getId());
        updated.setEmail("porque@gmail.com");
        managerDao.update(updated);

        assertEquals("Manager retrieved from DAO does not equal updated match",
                updated, entityManager.find(Manager.class, inserted.getId()));

    }

    @Test
    public void updateTwoManagers() {
        Manager insertedOne = manager1;
        Manager insertedTwo = manager2;
        entityManager.persist(insertedOne);
        entityManager.persist(insertedTwo);

        Manager updatedOne =  entityManager.find(Manager.class, insertedOne.getId());
        updatedOne.setEmail("updatedOne@gmail.com");
        managerDao.update(updatedOne);

        assertEquals("Manager retrieved from DAO does not equal to first updated match",
                updatedOne, entityManager.find(Manager.class, insertedOne.getId()));

        assertEquals("Manager retrieved from DAO does not equal to match that was not updated",
                insertedTwo, entityManager.find(Manager.class, insertedTwo.getId()));

        Manager updatedTwo = entityManager.find(Manager.class, insertedTwo.getId());
        updatedTwo.setEmail("stillloveyoubarca@gmail.com");
        managerDao.update(updatedTwo);

        assertEquals("Manager retrieved from DAO does not equal to second updated match",
                updatedTwo, entityManager.find(Manager.class, insertedTwo.getId()));

        assertEquals("Manager retrieved from DAO does not equal to match that was not updated",
                updatedOne, entityManager.find(Manager.class, updatedOne.getId()));
    }

    @Test
    public void deleteManager() {
        Manager inserted = manager1;
        entityManager.persist(inserted);
        managerDao.delete(inserted.getId());

        assertNull("Entity was not deleted",
                entityManager.find(Manager.class, inserted.getId()));
    }

    @Test
    public void deleteTwoManagers() {
        Manager insertedOne = manager1;
        Manager insertedTwo = manager2;
        entityManager.persist(insertedOne);
        entityManager.persist(insertedTwo);

        managerDao.delete(insertedOne.getId());

        assertNull("First manager was not deleted properly",
                entityManager.find(Manager.class, insertedOne.getId()));
        assertEquals("Manager retrieved from DAO does not equal to first match that was not deleted",
                insertedTwo, entityManager.find(Manager.class, insertedTwo.getId()));

        managerDao.delete(insertedTwo.getId());

        assertNull("Second manager was not deleted properly",
                entityManager.find(Manager.class, insertedTwo.getId()));
    }

    @Test
    public void fetchByIdManager() {
        Manager inserted = manager1;
        entityManager.persist(inserted);

        assertEquals("Manager retrieved from DAO does not equal expected result",
                inserted, managerDao.fetchById(inserted.getId()));
    }

    @Test
    public void fetchByIdTwoManager() {
        Manager insertedOne = manager1;
        Manager insertedTwo = manager2;
        entityManager.persist(insertedOne);
        entityManager.persist(insertedTwo);

        assertEquals("Manager retrieved from DAO does not equal to first manager",
                insertedOne, managerDao.fetchById(insertedOne.getId()));

        assertEquals("Manager retrieved from DAO does not equal to second manager",
                insertedTwo, managerDao.fetchById(insertedTwo.getId()));
    }

    @Test
    public void fetchAllManager() {
        Manager inserted = manager1;
        entityManager.persist(inserted);

        List<Manager> managers = managerDao.fetchAll();

        assertTrue("Length of list retrieved from DAO does not equal 1",
                managers != null && managers.size() == 1);

        assertEquals("Manager in list does not equal to manager inserted to DB",
                inserted, managers.get(0));
    }

    @Test
    public void fetchAllTwoManagers() {
        Manager insertedOne = manager1;
        Manager insertedTwo = manager2;
        entityManager.persist(insertedOne);
        entityManager.persist(insertedTwo);

        List<Manager> managers = managerDao.fetchAll();

        assertTrue("Length of list retrieved from DAO does not equal 2",
                managers != null && managers.size() == 2);

        assertTrue("List does not contain first manager",
                managers.contains(insertedOne));

        assertTrue("List does not contain second manager",
                managers.contains(insertedTwo));
    }

    @Test
    public void fetchByNameManager() {
        Manager insertedOne = manager1;
        Manager insertedTwo = manager2;
        entityManager.persist(insertedOne);
        entityManager.persist(insertedTwo);

        assertTrue("First manager should be retrieved from db",
                insertedOne.equals(managerDao.fetchByName("Jose Mourinho")));
    }

    @Test
    public void fetchByNameManagerNoResult() {
        Manager insertedOne = manager1;
        Manager insertedTwo = manager2;
        entityManager.persist(insertedOne);
        entityManager.persist(insertedTwo);

        assertNull("Fetch should return null",
                managerDao.fetchByName("Arsene Wenger"));
    }

    @Test
    public void fetchByNationalityManager() {
        Manager insertedOne = manager1;
        Manager insertedTwo = manager2;
        entityManager.persist(insertedOne);
        entityManager.persist(insertedTwo);

        List<Manager> managers = managerDao.fetchByNationality(NationalityEnum.Portugal);

        assertTrue("Length of list retrieved from DAO does not equal 1",
                managers != null && managers.size() == 1);

        assertTrue("Manager in list does not equal to manager inserted to DB",
                managers.contains(insertedOne));

        assertTrue("Second manager should not be in list",
                !managers.contains(insertedTwo));
    }

    @Test
    public void fetchByNationalityManagerNoResult() {
        Manager insertedOne = manager1;
        Manager insertedTwo = manager2;
        entityManager.persist(insertedOne);
        entityManager.persist(insertedTwo);

        List<Manager> managers = managerDao.fetchByNationality(NationalityEnum.CzechRepublic);

        assertTrue("Length of list retrieved from DAO does not equal 0",
                managers != null && managers.size() == 0);
    }

    @Test
    public void fetchByEmailManager() {
        Manager insertedOne = manager1;
        Manager insertedTwo = manager2;
        entityManager.persist(insertedOne);
        entityManager.persist(insertedTwo);

        assertTrue("Second manager should be retrieved from db",
                insertedTwo.equals(managerDao.fetchByEmail("tikitaka@gmail.com")));
    }

    @Test
    public void fetchByEmailManagerNoResult() {
        Manager insertedOne = manager1;
        Manager insertedTwo = manager2;
        entityManager.persist(insertedOne);
        entityManager.persist(insertedTwo);

        assertNull("Fetch should return null",
                managerDao.fetchByEmail("abcd@gmail.com"));
    }

    @Test
    public void fetchByTeamManager() {
        Manager insertedOne = manager1;
        Manager insertedTwo = manager2;
        entityManager.persist(insertedOne);
        entityManager.persist(insertedTwo);

        League league1 = new League.LeagueBuilder("La Liga", NationalityEnum.Spain).build();
        Team team1 = new Team.TeamBuilder("Real Madrid", NationalityEnum.Spain, league1)
                .manager(manager1)
                .build();
        Team team2 = new Team.TeamBuilder("FC Barcelona", NationalityEnum.Spain, league1)
                .manager(manager2)
                .build();
        entityManager.persist(league1);
        entityManager.persist(team1);
        entityManager.persist(team2);

        assertTrue("First manager should be retrieved from db",
                insertedOne.equals(managerDao.fetchByTeam(team1)));
    }

    @Test
    public void fetchByTeamManagerNoResult() {
        Manager insertedOne = manager1;
        Manager insertedTwo = manager2;
        entityManager.persist(insertedOne);
        entityManager.persist(insertedTwo);

        League league1 = new League.LeagueBuilder("La Liga", NationalityEnum.Spain).build();
        Team team1 = new Team.TeamBuilder("Real Madrid", NationalityEnum.Spain, league1)
                .manager(manager1)
                .build();
        Team team2 = new Team.TeamBuilder("FC Barcelona", NationalityEnum.Spain, league1)
                .manager(manager2)
                .build();

        Team team3 = new Team.TeamBuilder(
                "Atletico Madrid",
                NationalityEnum.Spain,
                league1).build();

        entityManager.persist(league1);
        entityManager.persist(team3);
        entityManager.persist(team2);
        entityManager.persist(team1);

        assertNull("Fetch should return null",
                managerDao.fetchByTeam(team3));
    }

    @Test
    public void fetchManagersWithoutTeam() {
        Manager insertedOne = manager1;
        Manager insertedTwo = manager2;
        entityManager.persist(insertedOne);
        entityManager.persist(insertedTwo);

        League league1 = new League.LeagueBuilder("La Liga", NationalityEnum.Spain).build();

        Team team1 = new Team.TeamBuilder("Real Madrid", NationalityEnum.Spain, league1)
                .manager(manager1)
                .build();
        Team team2 = new Team.TeamBuilder("FC Barcelona", NationalityEnum.Spain, league1)
                .build();
        entityManager.persist(league1);
        entityManager.persist(team1);
        entityManager.persist(team2);

        List<Manager> managers = managerDao.fetchManagersWithoutTeam();

        assertTrue("Length of list retrieved from DAO does not equal 1",
                managers != null && managers.size() == 1);

        assertTrue("First manager should not be in list",
                !managers.contains(insertedOne));

        assertTrue("Second manager should be in list",
                managers.contains(insertedTwo));

    }
}
