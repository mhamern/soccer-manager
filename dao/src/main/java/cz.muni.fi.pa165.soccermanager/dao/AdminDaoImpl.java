package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Admin;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 10/24/2017.
 */

@Repository
public class AdminDaoImpl implements AdminDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Admin fetchById(long adminId) {
        return manager.find(Admin.class, adminId);
    }

    @Override
    public List<Admin> fetchAll() {
        return manager.createQuery(
                "SELECT a FROM Admin a", Admin.class).getResultList();
    }

    @Override
    public void insert(Admin admin) {
        manager.persist(admin);
    }

    @Override
    public void update(Admin admin) {
        manager.merge(admin);
    }

    @Override
    public void delete(long adminId) {
        manager.remove(fetchById(adminId));
    }
}
