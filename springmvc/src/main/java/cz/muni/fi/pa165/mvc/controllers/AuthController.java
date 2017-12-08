package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.soccermanager.dto.AuthenticateManagerDTO;
import cz.muni.fi.pa165.soccermanager.dto.ManagerDTO;
import cz.muni.fi.pa165.soccermanager.facade.ManagerFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author 445720 Martin Hamernik
 * @version 12/3/2017.
 */
@Controller
@RequestMapping("/")
public class AuthController {

    private final ManagerFacade managerFacade;

    @Inject
    AuthController(ManagerFacade managerFacade) {
        this.managerFacade = managerFacade;
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("authenticatedUser") != null) {
            return "redirect:/";
        }

        model.addAttribute("userLogin", new AuthenticateManagerDTO());
        return "/auth/login";
    }

    @RequestMapping(value = "auth/login", method = RequestMethod.POST)
    public String postLogin(@Valid @ModelAttribute("userLogin") AuthenticateManagerDTO managerForm,
                        BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes,
                        UriComponentsBuilder uriBuilder, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                model.addAttribute(error.getField() + "_error", true);
            }

            model.addAttribute("userLogin", new AuthenticateManagerDTO());
            return "auth/login";
        }

        ManagerDTO found = managerFacade.findManagerByEmail(managerForm.getMail());

        if (found == null || !managerFacade.authenticate(managerForm)) {
            redirectAttributes.addFlashAttribute("alert_warning", "Login with email " + managerForm.getMail()
                    + " has failed. Wrong password?");
            return "redirect:" + uriBuilder.path("/auth/login").toUriString();
        }

        request.getSession().setAttribute("authenticatedUser", found);

        redirectAttributes.addFlashAttribute("alert_success", "Logged in successfully");
        return "redirect:" + uriBuilder.path("/").toUriString();
    }

    @RequestMapping(value = "/auth/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request) {
        request.getSession().removeAttribute("authenticatedUser");
        return "redirect:/";
    }


}
