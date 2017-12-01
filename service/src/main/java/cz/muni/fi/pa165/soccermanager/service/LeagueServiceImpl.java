package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dao.LeagueDao;
import cz.muni.fi.pa165.soccermanager.dao.MatchDao;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.service.exceptions.SoccerManagerServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static sun.audio.AudioPlayer.player;

/**
 * Implementation of the LeagueService. The class is the part of implementation of
 * the business logic of the application.
 * @author 476368 Iman Mehmandoust
 * @version 11/24/2017.
 */

@Service
public class LeagueServiceImpl implements LeagueService {


    @Inject
    private LeagueDao leagueDao;

    @Inject
    private MatchDao matchDao;


    @Override
    public League fetchById(long leagueId) {
        return leagueDao.fetchById(leagueId);
    }

    @Override
    public List<League> fetchAll() {
        return leagueDao.fetchAll();
    }

    @Override
    public League insert(League league) {
        leagueDao.insert(league);
        return league;
    }

    @Override
    public void update(League league) {
        leagueDao.update(league);
    }

    @Override
    public void addMatch(Match match,League league) throws SoccerManagerServiceException {
        if (match != null && league != null) {

            if (league == match.getHomeTeam().getLeague() &&
                    league == match.getAwayTeam().getLeague())
                throw new SoccerManagerServiceException(
                    "Teams are not from the same league.\'" +
                    match.getHomeTeam().getName() + " plays " +
                    match.getHomeTeam().getLeague() + '\'' +
                    match.getAwayTeam().getName() + " plays " +
                    match.getAwayTeam().getLeague() + '\''
                );
            if (!matchDao.fetchByLeague(league).contains(match)) {
                league.addMatch(match);
                leagueDao.update(league);
            } else {
                throw new SoccerManagerServiceException(
                        "In league " + league.getName() + " is already this match." + match.toString());
            }
        } else {
            throw new IllegalArgumentException("Match is null");
        }
    }

    @Override
    public void delete(long leagueId) {
        leagueDao.delete(leagueId);
    }
}
