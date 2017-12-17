package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.mvc.forms.CreateMatchDTOValidator;
import cz.muni.fi.pa165.soccermanager.dto.CreateMatchDTO;
import cz.muni.fi.pa165.soccermanager.dto.ManagerDTO;
import cz.muni.fi.pa165.soccermanager.dto.MatchDTO;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.facade.ManagerFacade;
import cz.muni.fi.pa165.soccermanager.facade.MatchFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * @author 456519 Filip Lux
 * @version 18/8/2017.
 */

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerFacade managerFacade;

    @Inject
    ManagerController(ManagerFacade managerFacade) {this.managerFacade = managerFacade;}

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("managers", managerFacade.findAllManagers());
        return "manager/list";
    }

    @RequestMapping(value = "admins", method = RequestMethod.GET)
    public String listAdmins(Model model) {
        model.addAttribute("finished", managerFacade.getAdmins());
        return "match/admins";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id,
                         Model model,
                         UriComponentsBuilder uriBuilder,
                         RedirectAttributes redirectAttributes) {
        ManagerDTO managerDTO = managerFacade.findManagerById(id);
        managerFacade.deleteManager(id);

        redirectAttributes.addFlashAttribute("alert_success", "Manager " + managerDTO.getName() + " was deleted.");
        return "redirect:" + uriBuilder.path("/manager/list").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("manager", managerFacade.findManagerById(id));
        return "manager/view";
    }

}
