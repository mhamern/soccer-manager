package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author 476368 Iman Mehmandoust
 * @version 11/22/2017
 */
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
    public League fetchByName(String name) {

        TypedQuery<League> query = manager.
                createQuery("SELECT l FROM League l WHERE l.name = :leagueName",
                        League.class);
        query.setParameter("leagueName", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException nre ) {
            return  null;
        }
    }

    @Override
    public List<League> fetchByCountry(NationalityEnum country) {

        TypedQuery<League> query = manager.
                createQuery("SELECT l FROM League l WHERE l.country = :leagueCountry",
                        League.class);
        query.setParameter("leagueCountry", country);
        try {
            return query.getResultList();
        } catch (NoResultException nre ) {
            return  null;
        }
    }

    @Override
    public List<Match> fetchByLeague(League league) {
        return null;
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
