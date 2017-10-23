package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Match;

import java.util.List;

public interface MatchDao {
    Match fetchById(long matchId);
    List<Match> fetchAll();
    void insert(Match player);
    void update(Match player);
    void delete(long matchId);
}
