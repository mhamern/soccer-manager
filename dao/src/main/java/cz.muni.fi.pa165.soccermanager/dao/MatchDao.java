package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Match;

import java.util.List;

/**
 * @author 456519 Filip Lux
 * @version 10/27/2017.
 *
 * Data object access class of Match entity
 */
public interface MatchDao {
    /**
     * finds match by its id
     * @param matchId id of desired match
     * @return match from database if exists, null otherwise
     */
    Match fetchById(long matchId);

    /**
     * Gets all matches from db
     * @return list of all matches from db
     */
    List<Match> fetchAll();

    /**
     * Inserts new match in db
     * @param match instance of Match, that should be inserted
     */
    void insert(Match match);

    /**
     * Update match attributes in db, it is found by id
     * @param match instance of Match
     */
    void update(Match match);

    /**
     * Delete matc in db, it is found by id
     * @param matchId id of match that should be deleted
     */
    void delete(long matchId);
}
