package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.mvc.forms.CreateLeagueDTOValidator;
import cz.muni.fi.pa165.mvc.forms.CreateTeamDTOValidator;
import cz.muni.fi.pa165.soccermanager.dto.CreateLeagueDTO;
import cz.muni.fi.pa165.soccermanager.dto.CreateTeamDTO;
import cz.muni.fi.pa165.soccermanager.dto.LeagueDTO;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.facade.LeagueFacade;
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
import java.util.List;

/**
 * @author 476368 Iman Mehmandoust
 * @version 12/7/2017.
 */
@Controller
@RequestMapping("/league")
public class LeagueController {


    private final LeagueFacade leagueFacade;

    private final MatchFacade matchFacade;


    @Inject
    LeagueController( LeagueFacade leagueFacade, MatchFacade matchFacade) {
        this.leagueFacade = leagueFacade;
        this.matchFacade = matchFacade;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("leagues", leagueFacade.getAllLeagues());
        return "league/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id,
                         Model model,
                         UriComponentsBuilder uriBuilder,
                         RedirectAttributes redirectAttributes) {
        LeagueDTO leagueDTO = leagueFacade.getLeagueById(id);
        leagueFacade.deleteLeague(id);

        redirectAttributes.addFlashAttribute("alert_success", "League \"" + leagueDTO.getName() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/league/list").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("league", leagueFacade.getLeagueById(id));
        model.addAttribute("matches", matchFacade.getMatchesByTeam(id));
        model.addAttribute("leagueTable", leagueFacade.calculateLeagues(id));

        return "league/view";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLeague(Model model) {
        model.addAttribute("createLeague", new CreateLeagueDTO());

        return "league/new";
    }

    @ModelAttribute("countries")
    public NationalityEnum[] countries() {
        return NationalityEnum.values();
    }




    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof CreateLeagueDTO) {
            binder.addValidators(new CreateLeagueDTOValidator());
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("createLeague") CreateLeagueDTO form,
                         BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                         UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                model.addAttribute(error.getField() + "_error", true);
            }
            return "league/new";
        }

        Long id = leagueFacade.CreateLeague(form);
        redirectAttributes.addFlashAttribute("alert_success", " League " + form.getName() + " was created");
        return "redirect:" + uriBuilder.path("/league/view/{id}").buildAndExpand(id).encode().toUriString();
    }




}
