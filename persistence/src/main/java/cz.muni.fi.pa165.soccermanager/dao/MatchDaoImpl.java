package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
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
    public List<Match> fetchByDate(LocalDate date) {
        TypedQuery<Match> query = manager
                .createQuery("SELECT m FROM Match m WHERE m.date = :date", Match.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Match> fetchFinishedMatches() {
        TypedQuery<Match> query = manager.createQuery("SELECT m FROM Match m WHERE m.finished = 'true'", Match.class);
        return query.getResultList();
    }

    @Override
    public List<Match> fetchByTeam(Team team) {
       TypedQuery<Match> query = manager
                .createQuery("SELECT m FROM Match m WHERE m.homeTeam.name = :teamName OR m.awayTeam.name = :teamName", Match.class);
       query.setParameter("teamName", team.getName());
       return query.getResultList();
    }

    @Override
    public List<Match> fetchByStadium(StadiumEnum stadium) {
        TypedQuery<Match> query = manager
                .createQuery("SELECT m FROM Match m WHERE m.stadium = :stadium", Match.class);
        query.setParameter("stadium", stadium);
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

    @Override
    public Boolean isFinished(Match match)  {
        long matchId = match.getId();
        return manager.find(Match.class, matchId).isFinished();
    }
}
