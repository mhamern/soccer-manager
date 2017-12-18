package cz.muni.fi.pa165.mvc.forms;

import cz.muni.fi.pa165.soccermanager.dto.CreateManagerDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 456519 Filip Lux
 * @version 12/8/2017.
 */
public class CreateManagerDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> _class) {
        return CreateManagerDTO.class.isAssignableFrom(_class);
    }

    @Override
    public void validate (Object object, Errors errors) {
        CreateManagerDTO createMatchDTO = (CreateManagerDTO) object;

        if (createMatchDTO.getName() == null || createMatchDTO.getName().isEmpty()) errors.rejectValue("name", "nameNull");
        if (createMatchDTO.getNationality() == null) errors.rejectValue("nationality", "nationalityNull");
        if (createMatchDTO.getEmail() == null || createMatchDTO.getEmail().isEmpty()) errors.rejectValue("email", "emailNull");
    }

}
