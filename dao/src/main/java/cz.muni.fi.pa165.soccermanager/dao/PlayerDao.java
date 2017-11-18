package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;

import java.util.List;

public interface PlayerDao {

    /**
     * @return found player if exists, null otherwise
     */
    Player fetchById(long playerId);

    /**
     * Retrieves all players in DB
     * @return List of players in DB
     */
    List<Player> fetchAll();

    /**
     * Inserts new player to db
     * @param player player to insert
     */
    void insert(Player player);

    /**
     * Updated values of player in db
     * @param player player to update
     */
    void update(Player player);

    /**
     * Deletes player with matching id
     * @param playerId id of player which shall be deleted
     */
    void delete(long playerId);

    /**
     * Retrieves all players playing in provided team
     * @param team team of players in db
     * @return List of players playing for provided team, empty list if no such player is in db
     */
    List<Player> fetchByTeam(Team team);

    /**
     * Retrieves all players playing on provided position
     * @param position position of players in db
     * @return List of players playing on provided position, empty list if no such player is in db
     */
    List<Player> fetchByPosition(PositionEnum position);

    /**
     * Retrieves all players with provided nationality
     * @param nationality nationality of players in db
     * @return List of players playing with provided nationality, empty list if no such player is in db
     */
    List<Player> fetchByNationality(NationalityEnum nationality);

    /**
     * Retrieves all free agent players (players not playing for any team)
     * @return List of players not playing for any team, empty list if no such player is in db
     */
    List<Player> fetchFreeAgents();

    /**
     * Retrieves player with provided name
     * @param name name of player in db
     * @return Player with matching name, null otherwise
     */
    Player fetchByName(String name);

}
