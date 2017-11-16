package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Manager;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;

import java.util.List;


/**
 * @author 445720 Martin Hamerník
 * @version 10/24/2017.
 *
 * Data object access class of Team entity
 */
public interface TeamDao {

    /**
     * Finds team in db by id.
     * @param teamId id of desired team
     * @return team retrieved from db
     */
    Team fetchById(long teamId);

    /**
     * Retrieves all teams from db
     * @return List of teams retrieved from db
     */
    List<Team> fetchAll();

    /**
     * Retrieves team matching provided name from db
     * @param name name of team
     * @return team retrieved from db, null if no such team is in db
     */
    Team fetchByName(String name);

    /**
     * Retrieves team managed by provided manager from db
     * @param manager manager of team
     * @return team retrieved from db, null if no such team is in db
     */
    Team fetchByManager(Manager manager);

    /**
     * Retrieves teams matching provided origin from db
     * @param origin Origin (country) of team
     * @return List of teams retrieved from db, empty list if no such team is in db
     */
    List<Team> fetchByOrigin(NationalityEnum origin);

    /**
     * Retrieves teams participating in provided league
     * @param league league of team
     * @return List of teams retrieved from db, empty list if no such team is in db
     */
    List<Team> fetchByLeague(League league);

    /**
     * Retrieves teams currently without manager
     * @return List of teams retrieved from db without manager, empty list if no such team is in db
     */
    public List<Team> fetchTeamsWithoutManager();

    /**
     * Inserts new team to db
     * @param team instance of team which shall be inserted
     *
     */
    void insert(Team team);

    /**
     * Finds team by id and updates it with new attributes.
     * @param team team already stored in db which shall be updated
     */
    void update(Team team);

    /**
     * Finds team by id and deletes it from db
     * @param teamId id of team that shall be deleted
     */
    void delete(long teamId);

}
