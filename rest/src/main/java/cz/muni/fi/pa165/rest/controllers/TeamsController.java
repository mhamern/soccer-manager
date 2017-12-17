package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.exceptions.InvalidParameterException;
import cz.muni.fi.pa165.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.soccermanager.dto.*;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.facade.TeamFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/29/2017.
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_TEAMS)
public class TeamsController {

    private final TeamFacade teamFacade;

    @Inject
    TeamsController(TeamFacade teamFacade) {
        this.teamFacade = teamFacade;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TeamDTO> getTeams() {
        return teamFacade.getAllTeams();
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO getTeamById(@PathVariable("id") long id) {
        TeamDTO teamDTO = teamFacade.getTeamById(id);
        if (teamDTO == null) {
            throw new ResourceNotFoundException();
        }
        return teamDTO;
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteTeam(@PathVariable("id") long id) throws ResourceNotFoundException {
        try {
            teamFacade.deleteTeam(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value ="/league/{league_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TeamDTO> getTeamsByLeagueId(@PathVariable("league_id") long leagueId) {
        List<TeamDTO> teamDTOSs = teamFacade.getTeamsByLeague(leagueId);
        if (teamDTOSs.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return teamDTOSs;
    }

    @RequestMapping(value ="/origin/{origin}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TeamDTO> getTeamsByOrigin(@PathVariable("origin") NationalityEnum origin) {
        List<TeamDTO>  teamDTOS = teamFacade.getTeamsByOrigin(origin);
        if (teamDTOS.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return teamDTOS;
    }

    @RequestMapping(value ="/manager/{manager_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO getTeamByManagerId(@PathVariable("manager_id") long managerId) {
        TeamDTO teamDTO = teamFacade.getTeamByManager(managerId);
        if (teamDTO == null) {
            throw new ResourceNotFoundException();
        }
        return teamDTO;
    }

    @RequestMapping(value ="name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO getTeamByName(@PathVariable("name") String name) {
        TeamDTO teamDTO = teamFacade.getTeamByName(name);
        if (teamDTO == null) {
            throw new ResourceNotFoundException();
        }
        return teamDTO;
    }

    @RequestMapping(value = "/{id}/add_player/{playerId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO addPlayer(@PathVariable("id") long id, @PathVariable("playerId") long playerId)
            throws InvalidParameterException {
        try {
            teamFacade.addPlayer(playerId, id);
            return teamFacade.getTeamById(id);
        } catch (Exception ex) {
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/{id}/remove_player/{playerId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO removePlayer(@PathVariable("id") long id, @PathVariable("playerId") long playerId)
            throws InvalidParameterException {
        try {
            teamFacade.removePlayer(playerId, id);
            return teamFacade.getTeamById(id);
        } catch (Exception ex) {
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/{id}/assign_manager/{managerId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO assignManager(@PathVariable("id") long id, @PathVariable("managerId") long managerId)
            throws InvalidParameterException {
        try {
            teamFacade.assignManager(managerId, id);
            return teamFacade.getTeamById(id);
        } catch (Exception ex) {
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/{id}/remove_manager", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO removeManager(@PathVariable("id") long id)
            throws InvalidParameterException {
        try {
            teamFacade.removeManager(id);
            return teamFacade.getTeamById(id);
        } catch (Exception ex) {
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/{id}/join_league/{leagueId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO joinLeague(@PathVariable("id") long id, @PathVariable("leagueId") long leagueId)
            throws InvalidParameterException {
        try {
            teamFacade.joinLeague(leagueId, id);
            return teamFacade.getTeamById(id);
        } catch (Exception ex) {
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/{id}/leave_league", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO leaveLeague(@PathVariable("id") long id)
            throws InvalidParameterException {
        try {
            teamFacade.leaveLeague(id);
            return teamFacade.getTeamById(id);
        } catch (Exception ex) {
            throw new InvalidParameterException();
        }
    }

}
