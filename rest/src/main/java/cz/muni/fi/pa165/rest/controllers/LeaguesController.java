package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.exceptions.InvalidParameterException;
import cz.muni.fi.pa165.rest.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.soccermanager.dto.*;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.facade.LeagueFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * @author 476368 Iman Mehmandoust
 * @version 12/07/2017.
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_LEAGUES)
public class LeaguesController {

    private final LeagueFacade leagueFacade;

    @Inject
    LeaguesController(LeagueFacade leagueFacade) {
        this.leagueFacade = leagueFacade;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<LeagueDTO> getLeagues() {
        return leagueFacade.getAllLeagues();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final LeagueDTO getLeagueById(@PathVariable("id") long id) {
        LeagueDTO leagueDTO = leagueFacade.getLeagueById(id);
        if (leagueDTO == null) {
            throw new ResourceNotFoundException();
        }
        return leagueDTO;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteLeague(@PathVariable("id") long id) throws ResourceNotFoundException {
        try {
            leagueFacade.deleteLeague(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }


    @RequestMapping(value = "/country/{country}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<LeagueDTO> getLeaguesByOrigin(@PathVariable("country") NationalityEnum country) {
        List<LeagueDTO> leagueDTOS = leagueFacade.getLeaguesByCountry(country);
        if (leagueDTOS.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return leagueDTOS;
    }


    @RequestMapping(value = "name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final LeagueDTO getLeagueByName(@PathVariable("name") String name) {
        LeagueDTO leagueDTO = leagueFacade.getLeagueByName(name);
        if (leagueDTO == null) {
            throw new ResourceNotFoundException();
        }
        return leagueDTO;
    }

    @RequestMapping(value = "/{id}/add_match/{matchId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final LeagueDTO addMatch(@PathVariable("id") long id, @PathVariable("matchId") long matchId)
            throws InvalidParameterException {
        try {
            leagueFacade.addMatch(id, matchId);
            return leagueFacade.getLeagueById(id);
        } catch (Exception ex) {
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/{id}/remove_match/{matchId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final LeagueDTO removeMatch(@PathVariable("id") long id, @PathVariable("matchId") long matchId)
            throws InvalidParameterException {
        try {
            leagueFacade.removeMatch(id, matchId);
            return leagueFacade.getLeagueById(id);
        } catch (Exception ex) {
            throw new InvalidParameterException();
        }
    }
}

