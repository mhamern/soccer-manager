package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.exceptions.InvalidParameterException;
import cz.muni.fi.pa165.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.soccermanager.dto.*;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.facade.TeamFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
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

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO getTeamById(@PathVariable("id") long id) {
        TeamDTO teamDTO = teamFacade.getTeamById(id);
        if (teamDTO == null) {
            throw new ResourceNotFoundException();
        }
        return teamDTO;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_VALUE,
        produces =  MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO createTeam(@RequestBody CreateTeamDTO team) throws ResourceAlreadyExistingException {
        try {
            Long id = teamFacade.createTeam(team);
            return teamFacade.getTeamById(id);
        } catch (Exception ex) { //specify?
            throw new ResourceAlreadyExistingException();
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteTeam(@PathVariable("id") long id) throws ResourceNotFoundException {
        try {
            teamFacade.deleteTeam(id);
        } catch (Exception ex) { //specify?
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TeamDTO> getTeamsByLeagueId(@PathVariable("league_id") long leagueId) {
        List<TeamDTO> teamDTOSs = teamFacade.getTeamsByLeague(leagueId);
        if (teamDTOSs.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return teamDTOSs;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TeamDTO> getTeamsByOrigin(@PathVariable("origin") NationalityEnum origin) {
        List<TeamDTO>  teamDTOS = teamFacade.getTeamsByOrigin(origin);
        if (teamDTOS.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return teamDTOS;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO getTeamByManagerId(@PathVariable("manager_id") long managerId) {
        TeamDTO teamDTO = teamFacade.getTeamByManager(managerId);
        if (teamDTO == null) {
            throw new ResourceNotFoundException();
        }
        return teamDTO;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO getTeamByName(@PathVariable("name") String name) {
        TeamDTO teamDTO = teamFacade.getTeamByName(name);
        if (teamDTO == null) {
            throw new ResourceNotFoundException();
        }
        return teamDTO;
    }

    @RequestMapping(value = "/{id}/add_player", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO addPlayer(@PathVariable("id") long id, @RequestBody PlayerDTO player)
            throws InvalidParameterException {
        try {
            teamFacade.addPlayer(id, player.getId());
            return teamFacade.getTeamById(id);
        } catch (Exception ex) { //specify?
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/{id}/remove_player", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO removePlayer(@PathVariable("id") long id, @RequestBody PlayerDTO player)
            throws InvalidParameterException {
        try {
            teamFacade.removePlayer(id, player.getId());
            return teamFacade.getTeamById(id);
        } catch (Exception ex) { //specify?
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/{id}/assign_manager", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO assignManager(@PathVariable("id") long id, @RequestBody ManagerDTO manager)
            throws InvalidParameterException {
        try {
            teamFacade.assignManager(id, manager.getId());
            return teamFacade.getTeamById(id);
        } catch (Exception ex) { //specify?
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/{id}/remove_manager", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO removeManager(@PathVariable("id") long id)
            throws InvalidParameterException {
        try {
            teamFacade.removeManager(id);
            return teamFacade.getTeamById(id);
        } catch (Exception ex) { //specify?
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/{id}/join_league", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO addPlayer(@PathVariable("id") long id, @RequestBody LeagueDTO league)
            throws InvalidParameterException {
        try {
            teamFacade.joinLeague(id, league.getId());
            return teamFacade.getTeamById(id);
        } catch (Exception ex) { //specify?
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/{id}/leave_league", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO removePlayer(@PathVariable("id") long id)
            throws InvalidParameterException {
        try {
            teamFacade.leaveLeague(id);
            return teamFacade.getTeamById(id);
        } catch (Exception ex) { //specify?
            throw new InvalidParameterException();
        }
    }

}
