package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Manager;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.service.exceptions.SoccerManagerServiceException;

import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */
public interface TeamService {

    Team fetchById(long teamId);
    List<Team> fetchAll();
    Team fetchByName(String name);
    Team fetchByManager(Manager manager);
    List<Team> fetchByOrigin(NationalityEnum origin);
    List<Team> fetchByLeague(League league);
    public List<Team> fetchTeamsWithoutManager();
    void insert(Team team)throws SoccerManagerServiceException;
    void update(Team team) throws SoccerManagerServiceException;
    void delete(long teamId);
    public void addPlayer(Player player, Team team) throws SoccerManagerServiceException;
}
