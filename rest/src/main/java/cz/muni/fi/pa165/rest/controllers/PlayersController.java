package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.exceptions.InvalidParameterException;
import cz.muni.fi.pa165.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.soccermanager.dto.CreatePlayerDTO;
import cz.muni.fi.pa165.soccermanager.dto.PlayerDTO;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;
import cz.muni.fi.pa165.soccermanager.facade.PlayerFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/29/2017.
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_PLAYERS)
public class PlayersController {

    private final PlayerFacade playerFacade;

    @Inject
    PlayersController(PlayerFacade playerFacade) {
        this.playerFacade = playerFacade;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PlayerDTO> getPlayers() {
        return playerFacade.getAllPlayers();
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PlayerDTO getPlayerById(@PathVariable("id") long id) {
        PlayerDTO playerDTO = playerFacade.getPlayerById(id);
        if (playerDTO == null) {
            throw new ResourceNotFoundException();
        }
        return playerDTO;
    }

    @RequestMapping(value ="/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PlayerDTO getPlayerByName(@PathVariable("name") String name) {
        PlayerDTO playerDTO = playerFacade.getPlayerByName(name);
        if (playerDTO == null) {
            throw new ResourceNotFoundException();
        }
        return playerDTO;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public final PlayerDTO createPlayer(@RequestBody CreatePlayerDTO player) throws ResourceAlreadyExistingException {
        try {
            Long id = playerFacade.createPlayer(player);
            return  playerFacade.getPlayerById(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deletePlayer(@PathVariable("id") long id) throws ResourceNotFoundException {
        try {
            playerFacade.deletePlayer(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value ="/team/{team_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PlayerDTO> getPlayersByTeam(@PathVariable("team_id") long teamId) {
        List<PlayerDTO> playerDTOSs = playerFacade.getPlayersByTeam(teamId);
        if (playerDTOSs.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return playerDTOSs;
    }

    @RequestMapping(value ="/list/nationality/{nationality}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PlayerDTO> getPlayersByNationality(@PathVariable("nationality") NationalityEnum nationality) {
        List<PlayerDTO> playerDTOSs = playerFacade.getPlayersByNationality(nationality);
        if (playerDTOSs.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return playerDTOSs;
    }

    @RequestMapping(value ="/list/position/{position}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PlayerDTO> getPlayersByPosition(@PathVariable("position")PositionEnum position) {
        List<PlayerDTO> playerDTOSs = playerFacade.getPlayersByPosition(position);
        if (playerDTOSs.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return playerDTOSs;
    }

    @RequestMapping(value ="/list/freeagents",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PlayerDTO> getFreeAgents() {
        List<PlayerDTO> playerDTOSs = playerFacade.getFreeAgents();
        if (playerDTOSs.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return playerDTOSs;
    }

}
