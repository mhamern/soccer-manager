package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.soccermanager.dto.MatchDTO;
import cz.muni.fi.pa165.soccermanager.facade.MatchFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * @author 456519 Filip Lux
 * @version 17/12/2017.
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_PLAYERS)
public class MatchesController {

    private final MatchFacade matchFacade;

    @Inject
    MatchesController(MatchFacade matchFacade) {
        this.matchFacade = matchFacade;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<MatchDTO> getMatches() {
        return matchFacade.getAllMatches();
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MatchDTO getMatchById(@PathVariable("id") long id) {
        MatchDTO matchDTO = matchFacade.getMatchById(id);
        if (matchDTO == null) {
            throw new ResourceNotFoundException();
        }
        return matchDTO;
    }

    @RequestMapping(value ="/team/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<MatchDTO> getMatchByTeam(@PathVariable("id") Long id) {
        List<MatchDTO> matchesDTO = matchFacade.getMatchesByTeam(id);
        if (matchesDTO == null) {
            throw new ResourceNotFoundException();
        }
        return matchesDTO;
    }


    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteMatch(@PathVariable("id") long id) throws ResourceNotFoundException {
        try {
            matchFacade.deleteMatch(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value ="/finished", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<MatchDTO> getPlayersByTeam() {
        List<MatchDTO> matchesDTO = matchFacade.getFinishedMatches();
        if (matchesDTO == null) {
            throw new ResourceNotFoundException();
        }
        return matchesDTO;
    }

    @RequestMapping(value ="/league/{leagueId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<MatchDTO> getPlayersByNationality(@PathVariable("leagueId") Long id) {
        List<MatchDTO> matchesDTO = matchFacade.getMatchesByLeague(id);
        if (matchesDTO == null) {
            throw new ResourceNotFoundException();
        }
        return matchesDTO;
    }

}
