package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dao.LeagueDao;
import cz.muni.fi.pa165.soccermanager.dao.MatchDao;
import cz.muni.fi.pa165.soccermanager.dao.TeamDao;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.service.exceptions.SoccerManagerServiceException;
import javafx.collections.transformation.SortedList;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Implementation of the LeagueService. The class is the part of implementation of
 * the business logic of the application.
 * @author 476368 Iman Mehmandoust
 * @version 11/24/2017.
 */


@Service
public class LeagueServiceImpl implements LeagueService {

    private TeamService teamService;

    private LeagueDao leagueDao;
    private TeamDao teamDao;
    private MatchDao matchDao;

    @Inject
    public LeagueServiceImpl(TeamService teamService, LeagueDao leagueDao, TeamDao teamDao, MatchDao matchDao) {
        this.teamService = teamService;
        this.leagueDao = leagueDao;
        this.teamDao = teamDao;
        this.matchDao = matchDao;
    }

    @Override
    public League fetchById(long leagueId) {
        return leagueDao.fetchById(leagueId);
    }

    @Override
    public List<League> fetchByCountry(NationalityEnum country) {
        return leagueDao.fetchByCountry(country);

    }


    @Override
    public League fetchByName(String leagueName) {
        return leagueDao.fetchByName(leagueName);
    }

    @Override
    public List<Team> calculateLeagues(League league){
        if(league != null){
            List<Team> teams = teamDao.fetchByLeague(league);

            for (Team team: teams) {
                teamService.calculatePointsAndGoals(team);
            }
            return teams;
        }
        else {
            throw new IllegalArgumentException("League is null");
        }
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

            if (!matchDao.fetchByLeague(league).contains(match)) {
                league.addMatch(match);
                leagueDao.update(league);
            } else {
                throw new SoccerManagerServiceException(
                        "In league " + league.getName() + " is already this match." + match.toString());
            }
        } else {
            throw new IllegalArgumentException("Match or league is null");
        }
    }

    @Override
    public void removeMatch(Match match, League league) throws SoccerManagerServiceException {
        if (match != null && league != null) {

            if (!matchDao.fetchByLeague(league).contains(match)) {
                league.removeMatch(match);
                leagueDao.update(league);
            } else {
                throw new SoccerManagerServiceException(
                        "In league " + league.getName() + " there is no such a match to remove." + match.toString());
            }
        } else {
            throw new IllegalArgumentException("Match or league is null");
        }
    }

    @Override
    public void delete(long leagueId) {
        League league = leagueDao.fetchById(leagueId);
        for (Match match : matchDao.fetchByLeague(league)) {
            matchDao.delete(match.getId());
        }
        for (Team team: teamDao.fetchByLeague(league)) {
            team.setLeague(null);
        }

        leagueDao.delete(leagueId);
    }
}
