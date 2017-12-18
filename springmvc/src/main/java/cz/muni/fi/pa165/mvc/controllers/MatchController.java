package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.mvc.forms.CreateMatchDTOValidator;
import cz.muni.fi.pa165.soccermanager.dto.*;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.facade.LeagueFacade;
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
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author 456519 Filip Lux
 * @version 18/8/2017.
 */

@Controller
@RequestMapping("/match")
public class MatchController {

    private final MatchFacade matchFacade;
    private final LeagueFacade leagueFacade;
    private final TeamFacade teamFacade;

    @Inject
    MatchController(MatchFacade matchFacade,
                    LeagueFacade leagueFacade,
                    TeamFacade teamFacade) {
        this.matchFacade = matchFacade;
        this.leagueFacade = leagueFacade;
        this.teamFacade = teamFacade; }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {

        List<LeagueDTO> leagues = leagueFacade.getAllLeagues();
        model.addAttribute("matches", matchFacade.getAllMatches());


        return "match/list";
    }

    @RequestMapping(value = "/finished", method = RequestMethod.GET)
    public String listFinished(Model model) {
        model.addAttribute("finished", matchFacade.getFinishedMatches());
        return "match/finished";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id,
                         Model model,
                         UriComponentsBuilder uriBuilder,
                         RedirectAttributes redirectAttributes) {
        MatchDTO matchDTO = matchFacade.getMatchById(id);
        matchFacade.deleteMatch(id);

        redirectAttributes.addFlashAttribute("alert_success", "Match " + matchDTO.getBasicDescription() + " was deleted.");
        return "redirect:" + uriBuilder.path("/match/list").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model, HttpServletRequest request) {
        ManagerDTO managerDTO = (ManagerDTO) request.getSession().getAttribute("authenticatedUser");
        MatchDTO matchDTO = matchFacade.getMatchById(id);
        TeamDTO usersTeam = teamFacade.getTeamByManager(managerDTO.getId());

        model.addAttribute("match", matchDTO);
        model.addAttribute("isUsersMatch", usersTeam != null &&
                (usersTeam.equals(matchDTO.getHomeTeam()) || usersTeam.equals(matchDTO.getAwayTeam())));
        return "match/view";
    }


    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newMatch(Model model) {
        model.addAttribute("createMatch", new CreateMatchDTO());
        return "match/new";
    }

    @ModelAttribute("stadiums")
    public StadiumEnum[] stadiums() { return StadiumEnum.values(); }

    @ModelAttribute("leagues")
    public List<LeagueDTO> leagues() {
        return leagueFacade.getAllLeagues();
    }

    @ModelAttribute("teams")
    public List<TeamDTO> teams() {
        return teamFacade.getAllTeams();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof CreateMatchDTO) {
            binder.addValidators(new CreateMatchDTOValidator());
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("createMatch") CreateMatchDTO form,
                         BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                         UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                model.addAttribute(error.getField() + "_error", true);
            }
            return "/match/new";
        }

        Long id = matchFacade.createMatch(form);
        redirectAttributes.addFlashAttribute("alert_success", "Match was created successfully");
        return "redirect:" + uriBuilder.path("/match/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/play/{id}", method = RequestMethod.POST)
    public String play(@PathVariable long id, RedirectAttributes redirectAttributes,
                       UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        MatchDTO match = matchFacade.getMatchById(id);
        ManagerDTO managerDTO = (ManagerDTO) request.getSession().getAttribute("authenticatedUser");
        TeamDTO managersTeam = null;
        if (managerDTO != null) {
            managersTeam = teamFacade.getTeamByManager(managerDTO.getId());
        }

        if (match != null && match.getAwayTeam() != null && match.getHomeTeam() != null && managersTeam != null &&
                (match.getHomeTeam().equals(managersTeam)) || match.getAwayTeam().equals(managersTeam)) {

            matchFacade.play(id);
            redirectAttributes.addFlashAttribute("alert_success", "Match was played");
            return "redirect:" + uriBuilder.path("/match/view/{id}").buildAndExpand(id).toUriString();
        } else {
            redirectAttributes.addFlashAttribute("alert_warning", "Match cannot be played");
            return "redirect:" + uriBuilder.path("/match/list/").toUriString();
        }

    }
}
