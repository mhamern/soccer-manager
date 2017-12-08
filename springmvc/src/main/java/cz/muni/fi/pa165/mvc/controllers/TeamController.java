package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.mvc.forms.CreateTeamDTOValidator;
import cz.muni.fi.pa165.soccermanager.dto.CreateTeamDTO;
import cz.muni.fi.pa165.soccermanager.dto.LeagueDTO;
import cz.muni.fi.pa165.soccermanager.dto.TeamDTO;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.facade.LeagueFacade;
import cz.muni.fi.pa165.soccermanager.facade.MatchFacade;
import cz.muni.fi.pa165.soccermanager.facade.PlayerFacade;
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
 * @author 445720 Martin Hamernik
 * @version 12/1/2017.
 */
@Controller
@RequestMapping("/team")
public class TeamController {


    private final TeamFacade teamFacade;

    private final LeagueFacade leagueFacade;

    private final PlayerFacade playerFacade;

    private final MatchFacade matchFacade;

    @Inject
    TeamController(TeamFacade teamFacade, LeagueFacade leagueFacade, PlayerFacade playerFacade, MatchFacade matchFacade) {
        this.teamFacade = teamFacade;
        this.leagueFacade = leagueFacade;
        this.playerFacade = playerFacade;
        this.matchFacade = matchFacade;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("teams", teamFacade.getAllTeams());
        return "team/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id,
                         Model model,
                         UriComponentsBuilder uriBuilder,
                         RedirectAttributes redirectAttributes) {
        TeamDTO teamDTO = teamFacade.getTeamById(id);
        teamFacade.deleteTeam(id);

        redirectAttributes.addFlashAttribute("alert_success", "Team \"" + teamDTO.getName() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/team/list").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("team", teamFacade.getTeamById(id));
        model.addAttribute("players", playerFacade.getPlayersByTeam(id));
        model.addAttribute("matches", matchFacade.getMatchesByTeam(id));
        return "team/view";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newTeam(Model model) {
        model.addAttribute("createTeam", new CreateTeamDTO());
        return "team/new";
    }

    @ModelAttribute("countries")
    public NationalityEnum[] countries() {
        return NationalityEnum.values();
    }

    @ModelAttribute("leagues")
    public List<LeagueDTO> leagues() {
        return leagueFacade.getAllLeagues();
    }

    @ModelAttribute("stadiums")
    public StadiumEnum[] stadiums() {
        return StadiumEnum.values();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof CreateTeamDTO) {
            binder.addValidators(new CreateTeamDTOValidator());
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("createTeam") CreateTeamDTO form,
                         BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                         UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                model.addAttribute(error.getField() + "_error", true);
            }
            return "team/new";
        }

        Long id = teamFacade.createTeam(form);
        redirectAttributes.addFlashAttribute("alert_success", " Team " + form.getName() + " was created");
        return "redirect:" + uriBuilder.path("/team/view/{id}").buildAndExpand(id).encode().toUriString();
    }
}
