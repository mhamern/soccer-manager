package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dao.MatchDao;
import cz.muni.fi.pa165.soccermanager.dao.PlayerDao;
import cz.muni.fi.pa165.soccermanager.dao.TeamDao;
import cz.muni.fi.pa165.soccermanager.entity.*;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.service.exceptions.SoccerManagerServiceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */
@Service
public class TeamServiceImpl implements  TeamService {

    @Inject
    private TeamDao teamDao;

    @Inject
    private PlayerDao playerDao;

    @Inject
    private MatchDao matchDao;

    @Override
    public Team fetchById(long teamId) {
        return teamDao.fetchById(teamId);
    }

    @Override
    public List<Team> fetchAll() {
        return teamDao.fetchAll();
    }

    @Override
    public Team fetchByName(String name) {
        return teamDao.fetchByName(name);
    }

    @Override
    public Team fetchByManager(Manager manager) {
        return teamDao.fetchByManager(manager);
    }

    @Override
    public List<Team> fetchByOrigin(NationalityEnum origin) {
        return teamDao.fetchByOrigin(origin);
    }

    @Override
    public List<Team> fetchByLeague(League league) {
        return teamDao.fetchByLeague(league);
    }

    @Override
    public List<Team> fetchTeamsWithoutManager() {
        return teamDao.fetchTeamsWithoutManager();
    }

    @Override
    public Team create(Team team) throws SoccerManagerServiceException {
        if (teamDao.fetchAll().contains(team)) {
            throw new SoccerManagerServiceException(
                    "Team " + team.getName() + " already exists");
        } else {
            teamDao.insert(team);
            return team;
        }
    }

    @Override
    public void update(Team team) throws SoccerManagerServiceException {
        if (teamDao.fetchAll().contains(team)) {
            throw new SoccerManagerServiceException(
                    "Team  " + team.getName() + "already exists");
        } else {
            teamDao.insert(team);
        }
    }

    @Override
    public void delete(long teamId) {
        teamDao.delete(teamId);
    }

    @Override
    public void addPlayer(Player player, Team team) throws SoccerManagerServiceException {
        if (player != null && team != null) {
            if (!playerDao.fetchByTeam(team).contains(player)) {
                team.addPlayer(player);
                teamDao.update(team);
            } else {
                throw new SoccerManagerServiceException(
                        "Player " + player.getName() + " already plays for team " + team.getName());
            }
        } else {
            throw new IllegalArgumentException("Player or team is null");
        }
    }

    @Override
    public void removePlayer(Player player, Team team) throws SoccerManagerServiceException {
        if (player != null && team != null) {
            if (playerDao.fetchByTeam(team).contains(player)) {
                team.removePlayer(player);
                teamDao.update(team);
            } else {
                throw new SoccerManagerServiceException(
                        "Player " + player.getName() + " does not play for team " + team.getName());
            }
        } else {
            throw new IllegalArgumentException("Player or team is null");
        }
    }

    @Override
    public void assignManager(Manager manager, Team team) throws SoccerManagerServiceException {
        if (team != null && manager != null) {
            if (!team.equals(teamDao.fetchByManager(manager))) {
                team.setManager(manager);
            } else {
                throw new SoccerManagerServiceException(
                        "Manager " + manager.getName() + " does already train team " + team.getName()
                );
            }
        } else {
            throw new IllegalArgumentException("Manager or team is null");
        }
    }

    @Override
    public void removeManager(Manager manager, Team team) throws SoccerManagerServiceException {
        if (team != null && manager != null) {
            if (teamDao.fetchByManager(manager).equals(team)) {
                team.setManager(null);
            } else {
                throw new SoccerManagerServiceException(
                        "Manager " + manager.getName() + " does not train team " + team.getName()
                );
            }
        } else {
            throw new IllegalArgumentException("Manager or team is null");
        }
    }

    @Override
    public void joinLeague(League league, Team team) throws SoccerManagerServiceException {
        if (team != null && league != null) {
            if (!teamDao.fetchByLeague(league).contains(team)) {
                team.setLeague(league);
            } else {
                throw new SoccerManagerServiceException(
                        "Team " + team.getName() + " already participates in league " + league.getName()
                );
            }
        } else {
            throw new IllegalArgumentException("League or team is null");
        }
    }

    @Override
    public void leaveLeague(League league, Team team) throws SoccerManagerServiceException {
        if (team != null && league != null) {
            if (teamDao.fetchByLeague(league).contains(team)) {
                team.setManager(null);
            } else {
                throw new SoccerManagerServiceException(
                        "Team " + team.getName() + " does not participate in league " + league.getName()
                );
            }
        } else {
            throw new IllegalArgumentException("League or team is null");
        }
    }

    @Override
    public Team calculatePointsAndGoals(Team team) throws SoccerManagerServiceException {
        if (team != null) {
            List<Match> matches = matchDao.fetchByTeam(team);
            if (!matches.isEmpty()) {

                team.setPoints(0);
                team.setGoalsScored(0);
                team.setGoalsConceded(0);

                for (Match match: matches) {
                    addPoints(team, match);
                }

                return team;

            } else  {
                throw new SoccerManagerServiceException(
                        "Team " + team.getName() + " did not play any matches yet"
                );
            }
        } else {
            throw new IllegalArgumentException("Team is null");
        }
    }

    private void addPoints(Team team, Match match) {
        int scored;
        int conceded;
        if (team.equals(match.getHomeTeam())) {

            scored = match.getHomeTeamGoals();
            conceded = match.getAwayTeamGoals();

        } else {

            scored = match.getAwayTeamGoals();
            conceded = match.getHomeTeamGoals();
        }

        team.setGoalsScored(team.getGoalsScored() + scored);
        team.setGoalsConceded(team.getGoalsConceded() + conceded);

        if (scored == conceded) {
            team.setPoints(team.getPoints() + 1);
        }
        if (scored > conceded) {
            team.setPoints(team.getPoints() + 3);
        }
    }
}
