package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.League;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class LeagueDaoImpl implements LeagueDao {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public League fetchById(long leagueId) {
        return manager.find(League.class, leagueId);
    }

    @Override
    public List<League> fetchAll() {
        TypedQuery<League> query = manager.createQuery("SELECT l FROM League l", League.class);
        return query.getResultList();
    }



    @Override
    public void insert(League league) {
        manager.persist(league);
    }

    @Override
    public void update(League league) {
        manager.merge(league);
    }

    @Override
    public void delete(long leagueId) {
        manager.remove(fetchById(leagueId));
    }
}
