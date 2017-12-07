package cz.muni.fi.pa165.mvc.forms;


import cz.muni.fi.pa165.soccermanager.dto.CreateLeagueDTO;
import cz.muni.fi.pa165.soccermanager.dto.CreateTeamDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 476368 Iman Mehmandoust
 * @version 12/7/2017.
 */
public class CreateLeagueDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateLeagueDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateLeagueDTO createLeagueDTO = (CreateLeagueDTO) target;

        if (createLeagueDTO.getName() == null || createLeagueDTO.getName().isEmpty()) errors.rejectValue("name", "nameNull");
        if (createLeagueDTO.getCountry() == null) errors.rejectValue("country", "countryNull");
    }
}
