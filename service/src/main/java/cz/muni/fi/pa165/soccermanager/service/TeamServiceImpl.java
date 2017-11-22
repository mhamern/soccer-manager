package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dao.PlayerDao;
import cz.muni.fi.pa165.soccermanager.dao.TeamDao;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Manager;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
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
    public void insert(Team team) throws SoccerManagerServiceException {
        if (teamDao.fetchAll().contains(team)) {
            throw new SoccerManagerServiceException(
                    "Team " + team.getName() + " already exists");
        } else {
            teamDao.insert(team);
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
        if (playerDao.fetchByTeam(team).contains(player)) {
            throw new SoccerManagerServiceException(
                    "Player " + player.getName() + " already plays for team " + team.getName());
        }
        else {
            team.addPlayer(player);
            teamDao.update(team);
        }
    }
}
