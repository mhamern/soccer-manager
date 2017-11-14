package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;

import java.util.List;

public interface PlayerDao {

    /**
     * @return found player if exists, null otherwise
     */
    Player fetchById(long playerId);
    List<Player> fetchAll();
    void insert(Player player);
    void update(Player player);
    void delete(long playerId);
    List<Player> fetchByTeam(Team team);
}
