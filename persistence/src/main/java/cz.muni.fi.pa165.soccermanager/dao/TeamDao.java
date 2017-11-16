package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Team;

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
