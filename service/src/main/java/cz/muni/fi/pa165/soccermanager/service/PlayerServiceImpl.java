package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dao.PlayerDao;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerDao playerDao;

    @Inject
    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public Player fetchById(long playerId) {
        return playerDao.fetchById(playerId);
    }

    @Override
    public List<Player> fetchAll() {
        return playerDao.fetchAll();
    }

    @Override
    public Player create(Player player) {
        if (player != null) {
            playerDao.insert(player);
            return player;
        } else {
            throw new IllegalArgumentException("Player is null");
        }
    }

    @Override
    public void update(Player player) {
        if (player != null) {
            playerDao.update(player);
        } else {
            throw new IllegalArgumentException("Player is null");
        }
    }

    @Override
    public void delete(long playerId) {
        playerDao.delete(playerId);
    }

    @Override
    public List<Player> fetchByTeam(Team team) {
        return playerDao.fetchByTeam(team);
    }

    @Override
    public List<Player> fetchByPosition(PositionEnum position) {
        return playerDao.fetchByPosition(position);
    }

    @Override
    public List<Player> fetchByNationality(NationalityEnum nationality) {
        return playerDao.fetchByNationality(nationality);
    }

    @Override
    public List<Player> fetchFreeAgents() {
        return playerDao.fetchFreeAgents();
    }

    @Override
    public Player fetchByName(String name) {
        return playerDao.fetchByName(name);
    }
}
