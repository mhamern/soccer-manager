package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.rest.ApiError;
import cz.muni.fi.pa165.rest.exceptions.ResourceAlreadyExistingException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;

/**
 * @author 445720 Martin Hamernik
 * @version 11/29/2017.
 */

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    ApiError handleResourceAlreadyExistingException(ResourceAlreadyExistingException ex) {
        ApiError apiError = new ApiError();
        apiError.setErrors(Arrays.asList("Requested resource already exists"));
        return apiError;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    ApiError handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ApiError apiError = new ApiError();
        Class<?> type = ex.getRequiredType();

        if (type.isEnum()) {
            apiError.setErrors(Arrays.asList("The parameter " + ex.getName() + " must have a value among: "
                    + StringUtils.join(type.getEnumConstants(), ", ")));
        } else {
            apiError.setErrors(Arrays.asList("The parameter " + ex.getName() + " must be of type " + type.getTypeName()));
        }

        return apiError;
    }
}
