package cz.muni.fi.pa165.soccermanager.dao;

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
public class TeamDaoImpl implements TeamDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Team fetchById(long teamId) {
        return manager.find(Team.class, teamId);
    }

    @Override
    public List<Team> fetchAll() {
        return manager.createQuery(
                "SELECT t FROM Team t", Team.class).getResultList();
    }

    @Override
    public void insert(Team team) {
        manager.persist(team);
    }

    @Override
    public void update(Team team) {
        manager.merge(team);
    }

    @Override
    public void delete(long teamId) {
        manager.remove(fetchById(teamId));
    }
}
