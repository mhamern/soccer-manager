package cz.muni.fi.pa165.soccermanager.facade;

import cz.muni.fi.pa165.soccermanager.dto.PlayerDTO;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;

import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */
public interface PlayerFacade {
    public List<PlayerDTO> getAllPlayers();
    public List<PlayerDTO> getPlayersByTeam(Long teamId);
    public List<PlayerDTO> getPlayersByLeague(Long leagueId);
    public List<PlayerDTO> getPlayersByNationality(NationalityEnum nationality);
    public List<PlayerDTO> getPlayersByPosition(PositionEnum position);
    public List<PlayerDTO> getFreeAgents();
    public PlayerDTO getPlayerById(Long id);
    public PlayerDTO getPlayerByName(String name);
    public void deletePlayer(Long playerId);
    public void changePosition(PositionEnum position);
}
