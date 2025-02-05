package amirgol.coach.participants.application.exception;

import amirgol.coach.common.exception.ParticipantBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // Example: 404 Not Found for "not found" exceptions
public class ParticipantNotFoundException extends ParticipantBaseException {
    public ParticipantNotFoundException(String message) {
        super(message);
    }
}