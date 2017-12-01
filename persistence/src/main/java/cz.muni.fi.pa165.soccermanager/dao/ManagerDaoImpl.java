package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Manager;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    public List<Manager> fetchByNationality(NationalityEnum nationalityEnum) {
        TypedQuery<Manager> query = entityManager.createQuery(
                "SELECT m FROM Manager m WHERE m.nationality = :nationality", Manager.class);
        query.setParameter("nationality", nationalityEnum);
        return query.getResultList();
    }

    @Override
    public Manager fetchByName(String name) {
        TypedQuery<Manager> query = entityManager.createQuery(
                "SELECT m FROM Manager m WHERE m.name = :managerName", Manager.class);
        query.setParameter("managerName", name);

        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Manager fetchByEmail(String email) {
        TypedQuery<Manager> query = entityManager.createQuery(
                "SELECT m FROM Manager m WHERE m.email = :managerEmail", Manager.class);
        query.setParameter("managerEmail", email);

        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Manager fetchByTeam(Team team) {
        TypedQuery<Manager> query = entityManager.createQuery(
                "SELECT m FROM Manager m, Team t WHERE t = :team AND t.manager = m", Manager.class);
        query.setParameter("team", team);

        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Manager> fetchManagersWithoutTeam() {
        return entityManager.createQuery(
                "SELECT m FROM Manager m, Team t WHERE NOT t.manager = m", Manager.class)
                .getResultList();
    }

    @Override
    public List<Manager> fetchAdmins() {
        return entityManager.createQuery(
                "SELECT m FROM Manager m WHERE m.admin = 'true'", Manager.class)
                .getResultList();
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
