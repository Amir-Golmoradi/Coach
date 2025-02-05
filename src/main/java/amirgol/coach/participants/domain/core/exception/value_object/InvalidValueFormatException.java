package amirgol.coach.participants.domain.core.exception.value_object;

import amirgol.coach.common.exception.ParticipantBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidValueFormatException extends ParticipantBaseException {
    public InvalidValueFormatException(String message) {
        super(message);
    }
}
