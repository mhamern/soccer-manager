package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
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
        TypedQuery<Player> query = manager
                .createQuery("SELECT p FROM Player p", Player.class);
        return query.getResultList();
    }

    @Override
    public List<Player> fetchByTeam(Team team) {
        TypedQuery<Player> query =  manager
                .createQuery("SELECT p FROM Player p, Team t WHERE t.name = :teamName AND p MEMBER OF t.players", Player.class);
        query.setParameter("teamName", team.getName());
        return query.getResultList();
    }

    @Override
    public List<Player> fetchByPosition(PositionEnum position) {
        TypedQuery<Player> query = manager
                .createQuery("SELECT p FROM Player p WHERE p.position = :playerPosition", Player.class);
        query.setParameter("playerPosition", position);
        return query.getResultList();
    }

    @Override
    public List<Player> fetchByNationality(NationalityEnum nationality) {
        TypedQuery<Player> query = manager
                .createQuery("SELECT p FROM Player p WHERE p.nationality = :playerNationality", Player.class);
        query.setParameter("playerNationality", nationality);
        return query.getResultList();
    }

    @Override
    public Player fetchByName(String name) {
        TypedQuery<Player> query = manager
                .createQuery("SELECT p FROM Player p WHERE p.name = :playerName", Player.class);
        query.setParameter("playerName", name);

        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Player> fetchFreeAgents() {
        return manager.
                createQuery("SELECT p FROM Player p, Team t " +
                        "WHERE p NOT MEMBER OF t.players", Player.class)
                .getResultList();
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

