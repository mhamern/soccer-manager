package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.mvc.forms.CreateMatchDTOValidator;
import cz.muni.fi.pa165.soccermanager.dto.*;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.facade.ManagerFacade;
import cz.muni.fi.pa165.soccermanager.facade.MatchFacade;
import cz.muni.fi.pa165.soccermanager.facade.TeamFacade;
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
import java.util.List;

/**
 * @author 456519 Filip Lux
 * @version 18/8/2017.
 */

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerFacade managerFacade;
    private final TeamFacade teamFacade;

    @Inject
    ManagerController(ManagerFacade managerFacade,
                      TeamFacade teamFacade) {
        this.managerFacade = managerFacade;
        this.teamFacade = teamFacade;}

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("managers", managerFacade.findAllManagers());
        model.addAttribute("admins", managerFacade.getAdmins());
        model.addAttribute("teams", teamFacade.getAllTeams());
        return "manager/list";
    }

    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    public String listAdmins(Model model) {
        model.addAttribute("finished", managerFacade.getAdmins());
        return "manager/admins";
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
        ManagerDTO managerDTO = managerFacade.findManagerById(id);
        model.addAttribute("manager", managerDTO);

        TeamDTO teamDTO = teamFacade.getTeamByManager(managerDTO.getId());
        if (teamDTO != null) {
            model.addAttribute("managersTeam", teamDTO);
        }
        return "manager/view";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("createManager") CreateManagerDTO form,
                         BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                         UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                model.addAttribute(error.getField() + "_error", true);
            }
            return "/manager/new";
        }

        Long id = managerFacade.registerManager(form, form.getEmail());
        redirectAttributes.addFlashAttribute("alert_success", "Manager was created successfully");
        return "redirect:" + uriBuilder.path("/manager/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newManager(Model model) {
        model.addAttribute("createManager", new CreateManagerDTO());
        return "manager/new";
    }

    @ModelAttribute("countries")
    public NationalityEnum[] countries() {
        return NationalityEnum.values();
    }

}
