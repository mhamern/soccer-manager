package cz.muni.fi.pa165.soccermanager.service.exceptions;

/**
 * @author 445720 Martin Hamernik
 * @version 11/22/2017.
 */
public class SoccerManagerServiceException extends RuntimeException {
    public SoccerManagerServiceException() {
        super();
    }

    public SoccerManagerServiceException(String message) {
        super(message);
    }

    public SoccerManagerServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SoccerManagerServiceException(Throwable cause) {
        super(cause);
    }
}
