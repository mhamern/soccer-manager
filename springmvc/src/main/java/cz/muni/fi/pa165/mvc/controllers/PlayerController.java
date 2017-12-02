package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.mvc.forms.CreatePlayerDTOValidator;
import cz.muni.fi.pa165.soccermanager.dto.CreatePlayerDTO;
import cz.muni.fi.pa165.soccermanager.dto.CreateTeamDTO;
import cz.muni.fi.pa165.soccermanager.dto.PlayerDTO;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;
import cz.muni.fi.pa165.soccermanager.facade.PlayerFacade;
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
 * @author 445720 Martin Hamernik
 * @version 12/1/2017.
 */

@Controller
@RequestMapping("/player")
public class PlayerController {

    private final PlayerFacade playerFacade;

    @Inject
    PlayerController(PlayerFacade playerFacade) {
        this.playerFacade = playerFacade;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("players", playerFacade.getAllPlayers());
        return "player/list";
    }

    @RequestMapping(value = "/freeagents", method = RequestMethod.GET)
    public String listFreeAgents(Model model) {
        model.addAttribute("freeagents", playerFacade.getFreeAgents());
        return "player/freeagents";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder,
                         RedirectAttributes redirectAttributes) {
        PlayerDTO playerDTO = playerFacade.getPlayerById(id);
        playerFacade.deletePlayer(id);

        redirectAttributes.addFlashAttribute("alert_success", "Player " + playerDTO.getName() + " was deleted");
        return "redirect:" + uriBuilder.path("/player/list").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("player", playerFacade.getPlayerById(id));
        return "player/view";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newPlayer(Model model) {
        model.addAttribute("createPlayer", new CreatePlayerDTO());
        return "player/new";
    }

    @ModelAttribute("countries")
    public NationalityEnum[] countries() {
        return NationalityEnum.values();
    }

    @ModelAttribute("positions")
    public PositionEnum[] positions() {
        return PositionEnum.values();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof CreatePlayerDTO) {
            binder.addValidators(new CreatePlayerDTOValidator());
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("playerCreate") CreatePlayerDTO form,
                         BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                         UriComponentsBuilder urisBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError error: bindingResult.getFieldErrors()) {
                model.addAttribute(error.getField() + "_error", true);
            }
            return "team/new";
        }

        Long id = playerFacade.createPlayer(form);
        redirectAttributes.addFlashAttribute("alert_success", "Player " + form.getName() + " was created successfully");
        return "redirect: " + urisBuilder.path("player/view/{id}").buildAndExpand(id).encode().toUriString();
    }

}
