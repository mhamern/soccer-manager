package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.entity.Match;

import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */
public interface MatchService {
    Match fetchById(long matchId);
    List<Match> fetchAll();
    void insert(Match match);
    void update(Match match);
    void delete(long matchId);
}
