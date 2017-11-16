package cz.muni.fi.pa165.soccermanager.facade;

import cz.muni.fi.pa165.soccermanager.dto.TeamDTO;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;

import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */
public interface TeamFacade {
    public List<TeamDTO> getAllTeams();
    public List<TeamDTO> getTeamsByLeague(Long leagueId);
    public List<TeamDTO> getTeamsByOrigin(NationalityEnum nationality);
    public TeamDTO getTeamByManager(Long managerId);
    public TeamDTO getTeamById(Long id);
    public TeamDTO getTeamByName(String name);
    public void addPlayer(Long teamId, Long playerId);
    public void removePlayer(Long teamId, Long playerId);
    public void addManager(Long teamId, Long managerId);
    public void removeManager(Long teamId, Long managerId);
}
