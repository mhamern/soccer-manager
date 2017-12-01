package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;

import java.time.LocalDate;
import java.util.List;

/**
 * @author 456519 Filip Lux
 * @version 10/27/2017.
 *
 * Data object access class of Match entity
 */
public interface MatchDao {

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
     * Gets all matches played in specific date
     * @return list of matches played in that date
     */
    List<Match> fetchByDate(LocalDate date);

    /**
     * Gets all played matches
     * @return list of already played matches
     */
    List<Match> fetchFinishedMatches();

    /**
     * return matches of one team
     * @return list of matches of one team
     */
    List<Match> fetchByTeam(Team team);

    /**
     * Gets all matches on one stadium
     * @return list of matches played on one stadium
     */
    List<Match> fetchByStadium(StadiumEnum stadium);

    /**
     * Gets all matches in a league
     * @return list of matches from one league
     */
    List<Match> fetchByLeague(League league);

    /**
     * Gets true if match is finished
     * @return true if match is finished
     */
    Boolean isFinished(Match match);

}
