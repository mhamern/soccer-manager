package cz.muni.fi.pa165.mvc.forms;

import cz.muni.fi.pa165.soccermanager.dto.CreatePlayerDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

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

        if (createPlayerDTO.getName() == null || createPlayerDTO.getName().isEmpty())
            errors.rejectValue("name", "nameNull");

        if (createPlayerDTO.getName() != null && !createPlayerDTO.getName().matches("^[A-Za-z ]+$"))
            errors.rejectValue("name", "nameFormat");

        if (createPlayerDTO.getBirthDate() == null)
            errors.rejectValue("birthDate", "birthDateNull");

        if (createPlayerDTO.getBirthDate() != null && createPlayerDTO.getBirthDate().isAfter(LocalDate.now().minusYears(15)))
            errors.rejectValue("birthDate", "birthDateTooYoung");

        if (createPlayerDTO.getNationality() == null)
            errors.rejectValue("nationality", "nationalityNull");

        if (createPlayerDTO.getPosition() == null)
            errors.rejectValue("position", "positionNull");

        if (createPlayerDTO.getNumber() <= 0 || createPlayerDTO.getNumber() >= 100)
            errors.rejectValue("number", "numberFormat");

        if (createPlayerDTO.getSpeed() < 0 || createPlayerDTO.getSpeed() > 100)
            errors.rejectValue("speed", "speedFormat");

        if (createPlayerDTO.getShooting() < 0 || createPlayerDTO.getShooting() > 100)
            errors.rejectValue("shooting", "shootingFormat");

        if (createPlayerDTO.getStrength() < 0 || createPlayerDTO.getStrength() > 100)
            errors.rejectValue("strength", "strengthFormat");

        if (createPlayerDTO.getDefence() < 0 || createPlayerDTO.getDefence() > 100)
            errors.rejectValue("defence", "defenceFormat");

        if (createPlayerDTO.getPassing() < 0 || createPlayerDTO.getPassing() > 100)
            errors.rejectValue("passing", "passingFormat");

        if (createPlayerDTO.getGoalkeeping() < 0 || createPlayerDTO.getGoalkeeping() > 100)
            errors.rejectValue("goalkeeping", "goalkeepingFormat");
    }

}
