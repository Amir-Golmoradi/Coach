package amirgol.coach.participants.domain.core.exception.events;

import amirgol.coach.common.exception.ParticipantBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEmailException extends ParticipantBaseException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
