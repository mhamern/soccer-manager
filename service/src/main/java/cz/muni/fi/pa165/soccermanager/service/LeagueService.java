package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.entity.League;

import java.util.List;

/**
 * @author 476368 Iman Mehmandoust
 * @version 11/24/2017.
 */
public interface LeagueService {
    League fetchById(long leagueId);
    List<League> fetchAll();
    void insert(League league);
    void update(League league);
    void delete(long leagueId);
}
