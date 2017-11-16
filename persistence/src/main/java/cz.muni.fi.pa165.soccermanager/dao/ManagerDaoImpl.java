package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Manager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 10/29/2017.
 */
@Repository
public class ManagerDaoImpl implements ManagerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Manager fetchById(long managerId) {
        return entityManager.find(Manager.class, managerId);
    }

    @Override
    public List<Manager> fetchAll() {
        return entityManager.createQuery(
                "SELECT m FROM Manager m", Manager.class).getResultList();
    }

    @Override
    public void insert(Manager manager) {
        entityManager.persist(manager);
    }

    @Override
    public void update(Manager manager) {
        entityManager.merge(manager);
    }

    @Override
    public void delete(long managerId) {
        entityManager.remove(fetchById(managerId));
    }
}
