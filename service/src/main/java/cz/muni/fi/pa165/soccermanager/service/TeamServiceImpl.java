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

    private final TeamDao teamDao;

    private final PlayerDao playerDao;

    private final MatchDao matchDao;

    @Inject
    public TeamServiceImpl(TeamDao teamDao, PlayerDao playerDao, MatchDao matchDao) {
        this.teamDao = teamDao;
        this.playerDao = playerDao;
        this.matchDao = matchDao;
    }

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
        if (team != null) {
            if (teamDao.fetchAll().contains(team)) {
                throw new SoccerManagerServiceException(
                        "Team " + team.getName() + " already exists");
            } else {
                teamDao.insert(team);
                return team;
            }
        } else {
            throw new IllegalArgumentException("Team is null");
        }
    }

    @Override
    public void update(Team team) throws SoccerManagerServiceException {
        if (team != null) {
            if (teamDao.fetchAll().contains(team)) {
                throw new SoccerManagerServiceException(
                        "Team  " + team.getName() + " already exists");
            } else {
                teamDao.update(team);
            }
        } else {
            throw new IllegalArgumentException("Team is null");
        }
    }

    @Override
    public void delete(long teamId) {
        teamDao.delete(teamId);
    }

    @Override
    public void addPlayer(Player player, Team team) throws SoccerManagerServiceException {
        if (player != null && team != null) {
            if (!playerDao.fetchByTeam(team).contains(player)
                    && playerDao.fetchFreeAgents().contains(player)) {
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
            if (teamDao.fetchTeamsWithoutManager().contains(team)) {
                if (teamDao.fetchByManager(manager) == null) {
                    team.setManager(manager);
                    teamDao.update(team);
                } else {
                    throw new SoccerManagerServiceException(
                            "Manager " + manager.getName() + " does already train team another team"
                    );
                }
            } else {
                throw new SoccerManagerServiceException(
                        "Another manager already trains " + team.getName() + ". Remove manager first"
                );
            }
        } else {
            throw new IllegalArgumentException("Manager or team is null");
        }
    }

    @Override
    public void removeManager(Team team) throws SoccerManagerServiceException {
        if (team != null) {
                team.setManager(null);
                teamDao.update(team);
        } else {
            throw new IllegalArgumentException("Team is null");
        }
    }

    @Override
    public void joinLeague(League league, Team team) throws SoccerManagerServiceException {
        if (team != null && league != null) {
            if (team.getLeague() == null) {
                team.setLeague(league);
                teamDao.update(team);
            } else {
                throw new SoccerManagerServiceException(
                        "Team " + team.getName() + " already participates in league " + league.getName()
                                + ". Remove league first"
                );
            }
        } else {
            throw new IllegalArgumentException("League or team is null");
        }
    }

    @Override
    public void leaveLeague(Team team) {
        if (team != null) {
                team.setManager(null);
                teamDao.update(team);
        } else {
            throw new IllegalArgumentException("Team is null");
        }
    }

    @Override
    public Team calculatePointsAndGoals(Team team) {
        if (team != null) {
            List<Match> matches = matchDao.fetchByTeam(team);
            if (!matches.isEmpty()) {

                team.setPoints(0);
                team.setGoalsScored(0);
                team.setGoalsConceded(0);

                for (Match match: matches) {
                    if (match.isFinished()) {
                        addPoints(team, match);
                    }
                }
            }
            return team;

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
