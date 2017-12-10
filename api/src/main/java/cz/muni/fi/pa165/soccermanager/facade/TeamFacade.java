package cz.muni.fi.pa165.soccermanager.facade;

import cz.muni.fi.pa165.soccermanager.dto.CreateTeamDTO;
import cz.muni.fi.pa165.soccermanager.dto.TeamDTO;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;

import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/24/2017.
 */
public interface TeamFacade {

    Long createTeam(CreateTeamDTO team);

    void deleteTeam(Long teamId);

    List<TeamDTO> getAllTeams();

    List<TeamDTO> getTeamsByLeague(Long leagueId);

    List<TeamDTO> getTeamsByOrigin(NationalityEnum nationality);

    TeamDTO getTeamByManager(Long managerId);

    TeamDTO getTeamById(Long id);

    TeamDTO getTeamByName(String name);

    TeamDTO getTeamByPlayer(Long id);

    void addPlayer( Long playerId, Long teamId);

    void removePlayer(Long playerId, Long teamId);

    void assignManager(Long managerId, Long teamId);

    void removeManager(Long teamId);

    void joinLeague(Long leagueId, Long teamId);

    void leaveLeague(Long teamId);
}
