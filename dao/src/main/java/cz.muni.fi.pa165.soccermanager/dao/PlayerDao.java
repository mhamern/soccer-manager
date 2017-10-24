package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Player;

import java.util.List;

public interface PlayerDao {

    /**
     * @return found player if exists, null otherwise
     */
    Player fetchById(Long playerId);
    List<Player> fetchAll();
    void insert(Player player);
    void update(Player player);
    void delete(long playerId);
}
