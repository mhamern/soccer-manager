package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;

import java.util.List;


/**
 * @author 476368 Iman Mehmandoust
 * @version 11/22/2017.
 * this interface is implemented in leagueDaoImpl class
 */


public interface LeagueDao {

    /**
     * Finds league in db by id.
     * @param leagueId id of desired team
     * @return league retrieved from db
     */

    League fetchById(long leagueId);

    /**
     * Retrieves all leagues from db
     * @return List of leagues retrieved from db
     */
    List<League> fetchAll();

    /**
     * Retrieves team matching provided name from db
     * @param name name of league
     * @return league retrieved from db, null if no such league is in db
     */
    League fetchByName(String name);

    /**
     * Retrieves league matching provided country from db
     * @param country name of league
     * @return league retrieved from db, null if no such league is in db
     */
    List<League> fetchByCountry(NationalityEnum country);


    /**
     * Retrieves all matches  in provided league
     * @param league league of matches in db
     * @return List of matches  for provided league, empty list if no such match is in db
     */
    List<Match> fetchByLeague(League league);


    /**
     * Inserts new league to db
     * @param league instance of league which shall be inserted
     *
     */
    void insert(League league);

    /**
     * Finds league by id and updates it with new attributes.
     * @param league league already stored in db which shall be updated
     */
    void update(League league);

    /**
     * Finds league by id and deletes it from db
     * @param leagueId id of league that shall be deleted
     */
    void delete(long leagueId);
}
