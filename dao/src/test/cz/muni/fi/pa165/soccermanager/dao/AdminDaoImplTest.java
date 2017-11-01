package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.PersistentContext;
import cz.muni.fi.pa165.soccermanager.entity.Admin;
import cz.muni.fi.pa165.soccermanager.entity.Match;

import org.junit.Before;
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

/**
 * @author 445720 Martin Hamernik
 * @version 11/1/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistentContext.class)
@Transactional
public class AdminDaoImplTest {

    @Autowired
    private AdminDao adminDao;

    @PersistenceContext
    private EntityManager manager;

    private Admin admin1;
    private Admin admin2;

    @Before
    public void setUp() {
        admin1 = new Admin("Admin1");
        admin2 = new Admin("Admin2");
    }

    @Test
    public void testInsertAdmin() {
        Admin inserted = admin1;
        adminDao.insert(inserted);

        assertEquals("Admin retrieved from DAO does not equal expected result",
                inserted, manager.find(Admin.class, inserted.getId()));
    }

    @Test
    public void insertTwoAdmins() {
        Admin insertedOne = admin1;
        Admin insertedTwo = admin2;
        adminDao.insert(insertedOne);
        adminDao.insert(insertedTwo);

        assertEquals("First admin retrieved from DAO does not equal to expected result",
                insertedOne, manager.find(Admin.class, insertedOne.getId()));

        assertEquals("Second admin retrieved from DAO does not equal expected result",
                insertedTwo, manager.find(Admin.class, insertedTwo.getId()));
    }

    @Test
    public void updateAdmin() {
        Admin inserted = admin1;
        manager.persist(inserted);

        Admin updated = manager.find(Admin.class, inserted.getId());
        updated.setUsername("Franta");
        adminDao.update(updated);

        assertEquals("Admin retrieved from DAO does not equal updated match",
                updated, manager.find(Admin.class, inserted.getId()));

    }

    @Test
    public void updateTwoAdmins() {
        Admin insertedOne = admin1;
        Admin insertedTwo = admin2;
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        Admin updatedOne =  manager.find(Admin.class, insertedOne.getId());
        updatedOne.setUsername("Josef");
        adminDao.update(updatedOne);

        assertEquals("Admin retrieved from DAO does not equal to first updated match",
                updatedOne, manager.find(Admin.class, insertedOne.getId()));

        assertEquals("Admin retrieved from DAO does not equal to match that was not updated",
                insertedTwo, manager.find(Admin.class, insertedTwo.getId()));

        Admin updatedTwo = manager.find(Admin.class, insertedTwo.getId());
        updatedTwo.setUsername("Martin");
        adminDao.update(updatedTwo);

        assertEquals("Admin retrieved from DAO does not equal to second updated match",
                updatedTwo, manager.find(Admin.class, insertedTwo.getId()));

        assertEquals("Admin retrieved from DAO does not equal to match that was not updated",
                updatedOne, manager.find(Admin.class, updatedOne.getId()));
    }

    @Test
    public void deleteAdmin() {
        Admin inserted = admin1;
        manager.persist(inserted);
        adminDao.delete(inserted.getId());

        assertNull("Entity was not deleted",
                manager.find(Admin.class, inserted.getId()));
    }

    @Test
    public void deleteTwoAdmins() {
        Admin insertedOne = admin1;
        Admin insertedTwo = admin2;
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        adminDao.delete(insertedOne.getId());

        assertNull("Admin match was not deleted properly",
                manager.find(Admin.class, insertedOne.getId()));
        assertEquals("Admin retrieved from DAO does not equal to first match that was not deleted",
                insertedTwo, manager.find(Admin.class, insertedTwo.getId()));

        adminDao.delete(insertedTwo.getId());

        assertNull("Admin match was not deleted properly",
                manager.find(Admin.class, insertedTwo.getId()));
    }

    @Test
    public void fetchByIdAdmin() {
        Admin inserted = admin1;
        manager.persist(inserted);

        assertEquals("Admin retrieved from DAO does not equal expected result",
                inserted, adminDao.fetchById(inserted.getId()));
    }

    @Test
    public void fetchByIdTwoAdmin() {
        Admin insertedOne = admin1;
        Admin insertedTwo = admin2;
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        assertEquals("Admin retrieved from DAO does not equal to first match",
                insertedOne, adminDao.fetchById(insertedOne.getId()));

        assertEquals("Admin retrieved from DAO does not equal to second match",
                insertedTwo, adminDao.fetchById(insertedTwo.getId()));
    }

    @Test
    public void fetchAllAdmins() {
        Admin inserted = admin1;
        manager.persist(inserted);

        List<Admin> admins = adminDao.fetchAll();

        assertTrue("Length of list retrieved from DAO does not equal 1",
                admins != null && admins.size() == 1);

        assertEquals("Admin in list does not equal to match inserted to DB",
                inserted, admins.get(0));
    }

    @Test
    public void fetchAllTwoAdmins() {
        Admin insertedOne = admin1;
        Admin insertedTwo = admin2;
        manager.persist(insertedOne);
        manager.persist(insertedTwo);

        List<Admin> admins = adminDao.fetchAll();

        assertTrue("Length of list retrieved from DAO does not equal 2",
                admins != null && admins.size() == 2);

        assertTrue("List does not contain first admin",
                admins.contains(insertedOne));

        assertTrue("List does not contain second admin",
                admins.contains(insertedTwo));
    }

}
