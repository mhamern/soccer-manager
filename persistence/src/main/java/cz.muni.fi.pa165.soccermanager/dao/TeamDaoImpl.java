package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Manager;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
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

    @Override
    public Team fetchByName(String name) {
        TypedQuery<Team> query = manager.
                createQuery("SELECT t FROM Team t WHERE t.name = :teamName",
                        Team.class);
        query.setParameter("teamName", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException nre ) {
            return  null;
        }
    }

    @Override
    public Team fetchByManager(Manager teamManager) {
        TypedQuery<Team> query = manager
                .createQuery("SELECT t FROM Team t WHERE t.manager = :teamManager", Team.class);
        query.setParameter("teamManager", teamManager);
        try {
            return query.getSingleResult();
        } catch (NoResultException nre ) {
            return  null;
        }
    }

    @Override
    public List<Team> fetchByOrigin(NationalityEnum origin) {
        TypedQuery<Team> query = manager
                .createQuery("SELECT t FROM Team t WHERE t.origin = :teamOrigin", Team.class);
        query.setParameter("teamOrigin", origin);
        return query.getResultList();
    }

    @Override
    public List<Team> fetchByLeague(League league) {
        TypedQuery<Team> query = manager
                .createQuery("SELECT t FROM Team t WHERE t.league = :teamLeague", Team.class);
        query.setParameter("teamLeague", league);
        return query.getResultList();
    }

    @Override
    public Team fetchByPlayer(Player player) {
        TypedQuery<Team> query = manager.
                createQuery("SELECT t FROM Team t, Player p WHERE p.name = :name AND p MEMBER of t.players",
                        Team.class);
        query.setParameter("name", player.getName());
        try {
            return query.getSingleResult();
        } catch (NoResultException nre ) {
            return  null;
        }
    }

    @Override
    public List<Team> fetchTeamsWithoutManager() {
        return manager
                .createQuery("SELECT t FROM Team t WHERE manager IS NULL", Team.class).getResultList();
    }
}
