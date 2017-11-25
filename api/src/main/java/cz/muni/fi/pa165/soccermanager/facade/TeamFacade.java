package cz.muni.fi.pa165.soccermanager.facade;

import cz.muni.fi.pa165.soccermanager.dto.CreateTeamDTO;
import cz.muni.fi.pa165.soccermanager.dto.TeamDTO;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;

import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/24/2017.
 */
public interface TeamFacade {

    public Long createTeam(CreateTeamDTO team);

    public void deleteTeam(Long teamId);

    public List<TeamDTO> getAllTeams();

    public List<TeamDTO> getTeamsByLeague(Long leagueId);

    public List<TeamDTO> getTeamsByOrigin(NationalityEnum nationality);

    public TeamDTO getTeamByManager(Long managerId);

    public TeamDTO getTeamById(Long id);

    public TeamDTO getTeamByName(String name);

    public void addPlayer( Long playerId, Long teamId);

    public void removePlayer(Long playerId, Long teamId);

    public void assignManager(Long managerId, Long teamId);

    public void removeManager(Long teamId);

    public void joinLeague(Long leagueId, Long teamId);

    public void leaveLeague(Long teamId);
}
