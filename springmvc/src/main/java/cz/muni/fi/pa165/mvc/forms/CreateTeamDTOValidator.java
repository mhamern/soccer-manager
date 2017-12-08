package cz.muni.fi.pa165.mvc.forms;


import cz.muni.fi.pa165.soccermanager.dto.CreateTeamDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 445720 Martin Hamernik
 * @version 12/1/2017.
 */
public class CreateTeamDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateTeamDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateTeamDTO createTeamDTO = (CreateTeamDTO) target;

        if (createTeamDTO.getName() == null || createTeamDTO.getName().isEmpty()) errors.rejectValue("name", "nameNull");
        if (createTeamDTO.getOrigin() == null) errors.rejectValue("origin", "originNull");
        if (createTeamDTO.getLeagueId() == null) errors.rejectValue("league", "leagueNull");
        if (createTeamDTO.getStadium() == null) errors.rejectValue("stadium", "stadiumNull");
    }
}
