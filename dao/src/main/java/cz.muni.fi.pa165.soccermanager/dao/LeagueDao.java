package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.League;

import java.util.List;


/**
 * @author 476368 Iman Mehmandoust
 * @version 10/27/2017.
 * this interface is implemented in leagueDaoImpl class
 */


public interface LeagueDao {
    League fetchById(long leagueId);
    List<League> fetchAll();
    void insert(League league);
    void update(League league);
    void delete(long leagueId);
}
