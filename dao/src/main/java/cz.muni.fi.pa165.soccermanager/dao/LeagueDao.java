package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.League;

import java.util.List;

public interface LeagueDao {
    League fetchById(long leagueId);
    List<League> fetchAll();
    void insert(League league);
    void update(League league);
    void delete(long leagueId);
}
