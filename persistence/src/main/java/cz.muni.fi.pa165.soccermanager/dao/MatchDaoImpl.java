package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Match;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author 456519 Filip Lux
 * @version 10/27/2017
 */
@Repository
public class MatchDaoImpl implements MatchDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Match fetchById(long matchId) {
        return manager.find(Match.class, matchId);
    }

    @Override
    public List<Match> fetchAll() {
        TypedQuery<Match> query = manager.createQuery("SELECT m FROM Match m", Match.class);
        return query.getResultList();
    }

    @Override
    public void insert(Match match) {
        manager.persist(match);
    }

    @Override
    public void update(Match match) {
        manager.merge(match);
    }

    @Override
    public void delete(long playerId) {
        manager.remove(fetchById(playerId));
    }
}
