package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.soccermanager.dto.ManagerDTO;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.facade.ManagerFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * @author 456519 Filip Lux
 * @version 17/12/2017.
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_MANAGERS)
public class ManagersController {

    private final ManagerFacade managerFacade;

    @Inject
    ManagersController(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ManagerDTO> getManagers() {
        return managerFacade.findAllManagers();
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ManagerDTO getManagerById(@PathVariable("id") long id) {
        ManagerDTO managerDTO = managerFacade.findManagerById(id);
        if (managerDTO == null) {
            throw new ResourceNotFoundException();
        }
        return managerDTO;
    }

    @RequestMapping(value ="/team/{teamId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ManagerDTO getManagerByTeam(@PathVariable("teamId") long id) {
        ManagerDTO managerDTO = managerFacade.findManagerByTeam(id);
        if (managerDTO == null) {
            throw new ResourceNotFoundException();
        }
        return managerDTO;
    }

    @RequestMapping(value ="/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ManagerDTO getManagerByName(@PathVariable("name") String name) {
        ManagerDTO managerDTO = managerFacade.findManagerByName(name);
        if (managerDTO == null) {
            throw new ResourceNotFoundException();
        }
        return managerDTO;
    }


    @RequestMapping(value ="/email/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ManagerDTO getManagerByEmail(@PathVariable("email") String email) {
        ManagerDTO managerDTO = managerFacade.findManagerByEmail(email);
        if (managerDTO == null) {
            throw new ResourceNotFoundException();
        }
        return managerDTO;
    }



    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteManager(@PathVariable("id") long id) throws ResourceNotFoundException {
        try {
            managerFacade.deleteManager(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value ="/nationality/{nationality}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ManagerDTO> getManagersByNationality(@PathVariable("nationality") NationalityEnum nationality) {
        List<ManagerDTO> managersDTO = managerFacade.findManagersByNationality(nationality);
        if (managersDTO.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return managersDTO;
    }

}
