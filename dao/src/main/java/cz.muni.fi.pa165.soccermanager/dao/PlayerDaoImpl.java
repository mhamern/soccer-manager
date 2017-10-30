package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Player;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Jan Novak
 */
@Repository
public class PlayerDaoImpl implements PlayerDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Player fetchById(long playerId) {
        return manager.find(Player.class, playerId);
    }

    @Override
    public List<Player> fetchAll() {
        TypedQuery<Player> query = manager.createQuery("SELECT p FROM Player p", Player.class);
        return query.getResultList();
    }

    @Override
    public void insert(Player player) {
        manager.persist(player);
    }

    @Override
    public void update(Player player) {
        manager.merge(player);
    }

    @Override
    public void delete(long playerId) {
        manager.remove(fetchById(playerId));
    }
}
