package cz.muni.fi.pa165.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 445720 Martin Hamernik
 * @version 11/30/2017.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested resource was not found.")
public class ResourceNotFoundException extends RuntimeException {
}
