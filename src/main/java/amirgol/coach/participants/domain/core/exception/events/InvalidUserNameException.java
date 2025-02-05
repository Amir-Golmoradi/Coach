package amirgol.coach.participants.domain.core.exception.events;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import amirgol.coach.common.exception.ParticipantBaseException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserNameException extends ParticipantBaseException {
    public InvalidUserNameException(String message) {
        super(message);
    }
}
