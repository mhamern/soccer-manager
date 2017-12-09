package cz.muni.fi.pa165.mvc.forms;

import cz.muni.fi.pa165.soccermanager.dto.AuthenticateManagerDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 445720 Martin Hamernik
 * @version 12/9/2017.
 */
public class AuthValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AuthenticateManagerDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AuthenticateManagerDTO authenticateManagerDTO = (AuthenticateManagerDTO) target;

        if (authenticateManagerDTO.getMail() == null || authenticateManagerDTO.getMail().isEmpty()) errors.rejectValue("mail", "mailNull");
        if (authenticateManagerDTO.getPassword() == null) errors.rejectValue("password", "passwordNull");
        if (authenticateManagerDTO.getMail() != null && !authenticateManagerDTO.getMail().contains("@")) errors.rejectValue("mail", "wrongFormat");
    }
}
