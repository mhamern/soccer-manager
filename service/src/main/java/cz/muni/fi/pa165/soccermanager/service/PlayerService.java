package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.entity.Player;

import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */
public interface PlayerService {
    Player fetchById(long playerId);
    List<Player> fetchAll();
    void insert(Player player);
    void update(Player player);
    void delete(long playerId);
}
