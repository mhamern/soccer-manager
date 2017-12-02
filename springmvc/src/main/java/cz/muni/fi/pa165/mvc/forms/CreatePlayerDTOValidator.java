package cz.muni.fi.pa165.mvc.forms;

import cz.muni.fi.pa165.soccermanager.dto.CreatePlayerDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 445720 Martin Hamernik
 * @version 12/1/2017.
 */
public class CreatePlayerDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CreatePlayerDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreatePlayerDTO createPlayerDTO = (CreatePlayerDTO) target;

        if (createPlayerDTO.getName() == null) errors.rejectValue("name", "Players name is null");
        if (createPlayerDTO.getBirthDate() == null) errors.rejectValue("birthdate", "Players birthdate is null");
        if (createPlayerDTO.getNationality() == null) errors.rejectValue("nationality", "Players nationality is null");
        if (createPlayerDTO.getPosition() == null) errors.rejectValue("position", "Players position is null");
        if (createPlayerDTO.getNumber() == 0) errors.rejectValue("number", "Players number is 0");
        if (createPlayerDTO.getSpeed() == 0) errors.rejectValue("speed", "Players speed is 0");
        if (createPlayerDTO.getShooting() == 0) errors.rejectValue("shooting", "Players shooting is 0");
        if (createPlayerDTO.getStrength() == 0) errors.rejectValue("strength", "Players strength is 0");
        if (createPlayerDTO.getDefence() == 0) errors.rejectValue("defence", "Players defence is 0");
        if (createPlayerDTO.getPassing() == 0) errors.rejectValue("passing", "Players passing is 0");
        if (createPlayerDTO.getGoalkeeping() == 0) errors.rejectValue("goalkeeping", "Players goalkeeping is 0");
    }

}
