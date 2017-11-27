package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dao.MatchDao;
import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.service.exceptions.SoccerManagerServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

/**
 * Implementation of the MatchService. The class is the part of implementation of
 * the business logic of the application.
 * @author 456519 Filip Lux
 * @version 11/23/2017.
 */

@Service
public class MatchServiceImpl implements MatchService {

    private MatchDao matchDao;

    @Inject
    public MatchServiceImpl(MatchDao matchDao) { this.matchDao = matchDao; }

    @Override
    public Match fetchById(long matchId) {
        return matchDao.fetchById(matchId);
    }

    @Override
    public List<Match> fetchAll() {
        return matchDao.fetchAll();
    }

    @Override
    public List<Match> fetchByDate(LocalDate date) {
        return matchDao.fetchByDate(date);
    }

    @Override
    public List<Match> fetchByTeam(Team team) {
        return matchDao.fetchByTeam(team);
    }

    @Override
    public List<Match> fetchFinished() {return matchDao.fetchFinishedMatches();}

    @Override
    public List<Match> fetchByStadium(StadiumEnum stadium) {
        return matchDao.fetchByStadium(stadium);
    }

    @Override
    public Match createMatch(Match match) throws SoccerManagerServiceException {

        if (match != null) {
            if (matchDao.fetchAll().contains(match)) {
                throw new SoccerManagerServiceException(
                        "Match " + match.getHomeTeam().getName() +
                                " vs. " + match.getAwayTeam().getName() + " already exists");
            }

            Team homeTeam = match.getHomeTeam();
            Team awayTeam = match.getAwayTeam();
            LocalDate date = match.getDate();

            if (homeTeam.equals(awayTeam)) {
                throw new SoccerManagerServiceException(
                        "It is not allowed to create match between two same teams "
                                + homeTeam.getName() + ". ");
            } else if (date.isBefore(LocalDate.now())) {
                throw new SoccerManagerServiceException(
                        "It is not allowed to create match in the past "
                                + date + ". ");
            } else {
                matchDao.insert(match);
                return match;
            }
        }
        else
            throw new IllegalArgumentException();
    }

    @Override
    public boolean isFinished(Match match) throws SoccerManagerServiceException {

        if (!matchDao.fetchAll().contains(match)) {
            throw new SoccerManagerServiceException(
                    "Match " + match.getHomeTeam().getName() +
                            " vs. " + match.getAwayTeam().getName() + " do not exists.");
        }

        return matchDao.isFinished(match);
    }

    @Override
    public void updateMatch(Match match) throws SoccerManagerServiceException {

        if (match == null) {
            throw new SoccerManagerServiceException(
                    "Updated match can not be null.");
        } else
            matchDao.update(match);

    }

    @Override
    public void deleteMatch(Long matchId) {
        matchDao.delete(matchId);

    }
}
