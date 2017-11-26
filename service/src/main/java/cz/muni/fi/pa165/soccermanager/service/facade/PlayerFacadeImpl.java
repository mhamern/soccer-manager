package cz.muni.fi.pa165.soccermanager.service.facade;

import cz.muni.fi.pa165.soccermanager.dto.CreatePlayerDTO;
import cz.muni.fi.pa165.soccermanager.dto.PlayerDTO;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;
import cz.muni.fi.pa165.soccermanager.facade.PlayerFacade;
import cz.muni.fi.pa165.soccermanager.service.BeanMappingService;

import cz.muni.fi.pa165.soccermanager.service.PlayerService;
import cz.muni.fi.pa165.soccermanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */

@Service
@Transactional
public class PlayerFacadeImpl implements PlayerFacade {

    @Inject
    private PlayerService playerService;

    @Inject
    private TeamService teamService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long createPlayer(CreatePlayerDTO player) {
        Player mappedPlayer = beanMappingService.mapTo(player, Player.class);

        mappedPlayer.setName(player.getName());
        mappedPlayer.setPosition(player.getPosition());
        mappedPlayer.setBirthDate(player.getBirthDate());
        mappedPlayer.setNationality(player.getNationality());
        mappedPlayer.setNumber(player.getNumber());
        mappedPlayer.setDefence(player.getDefence());
        mappedPlayer.setGoalkeeping(player.getGoalkeeping());
        mappedPlayer.setPassing(player.getPassing());
        mappedPlayer.setShooting(player.getShooting());
        mappedPlayer.setSpeed(player.getSpeed());
        mappedPlayer.setStrength(player.getStrength());

        Player newPlayer = playerService.create(mappedPlayer);

        return newPlayer.getId();
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return beanMappingService.mapTo(playerService.fetchAll(), PlayerDTO.class);
    }

    @Override
    public List<PlayerDTO> getPlayersByTeam(Long teamId) {
        Team team = teamService.fetchById(teamId);
        return beanMappingService.mapTo(playerService.fetchByTeam(team), PlayerDTO.class);
    }

    @Override
    public List<PlayerDTO> getPlayersByNationality(NationalityEnum nationality) {
        return beanMappingService.mapTo(playerService.fetchByNationality(nationality), PlayerDTO.class);
    }

    @Override
    public List<PlayerDTO> getPlayersByPosition(PositionEnum position) {
        return beanMappingService.mapTo(playerService.fetchByPosition(position), PlayerDTO.class);
    }

    @Override
    public List<PlayerDTO> getFreeAgents() {
        return beanMappingService.mapTo(playerService.fetchFreeAgents(), PlayerDTO.class);
    }

    @Override
    public PlayerDTO getPlayerById(Long id) {
        Player player = playerService.fetchById(id);
        return (player == null) ? null : beanMappingService.mapTo(player, PlayerDTO.class);
    }

    @Override
    public PlayerDTO getPlayerByName(String name) {
        return beanMappingService.mapTo(playerService.fetchByName(name), PlayerDTO.class);
    }

    @Override
    public void deletePlayer(Long playerId) {
        playerService.delete(playerId);
    }

    @Override
    public void changePosition(PositionEnum position, Long playerId) {
        Player player = playerService.fetchById(playerId);
        if (player != null) {
            player.setPosition(position);
            playerService.update(player);
        }
    }
}
