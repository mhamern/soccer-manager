package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.mvc.forms.CreateMatchDTOValidator;
import cz.muni.fi.pa165.soccermanager.dto.CreateMatchDTO;
import cz.muni.fi.pa165.soccermanager.dto.ManagerDTO;
import cz.muni.fi.pa165.soccermanager.dto.MatchDTO;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
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
@RequestMapping("/match")
public class MatchController {

    private final MatchFacade matchFacade;

    @Inject
    MatchController(MatchFacade matchFacade) {this.matchFacade = matchFacade;}

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("matches", matchFacade.getAllMatches());
        return "match/list";
    }

    @RequestMapping(value = "finished", method = RequestMethod.GET)
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
        return "redirect:" + uriBuilder.path("/player/list").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        model.addAttribute("match", matchFacade.getMatchById(id));
        return "match/view";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newMatch(Model model) {
        model.addAttribute("createMatch", new CreateMatchDTO());
        return "match/new";
    }

    @ModelAttribute("stadiums")
    public StadiumEnum[] stadiums() { return StadiumEnum.values(); }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof CreateMatchDTO) {
            binder.addValidators(new CreateMatchDTOValidator());
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("matchCreate") CreateMatchDTO cMatchDTO,
                         BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                         UriComponentsBuilder urisBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError error: bindingResult.getFieldErrors()) {
                model.addAttribute(error.getField() + "_error", true);
            }
            return "team/new";
        }

        Long id = matchFacade.createMatch(cMatchDTO);
        redirectAttributes.addFlashAttribute("alert_success", "Match " + cMatchDTO.getBasicDescription() + " was created successfully");
        return "redirect: " + urisBuilder.path("match/view/{id}").buildAndExpand(id).encode().toUriString();
    }

}
