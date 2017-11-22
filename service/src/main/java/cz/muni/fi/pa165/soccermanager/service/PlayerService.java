package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;

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

    List<Player> fetchByTeam(Team team);

    List<Player> fetchByPosition(PositionEnum position);

    List<Player> fetchByNationality(NationalityEnum nationality);

    List<Player> fetchFreeAgents();

    Player fetchByName(String name);
}
