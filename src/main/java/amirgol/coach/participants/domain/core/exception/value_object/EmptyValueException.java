package amirgol.coach.participants.domain.core.exception.value_object;

import amirgol.coach.common.exception.ParticipantBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class EmptyValueException extends ParticipantBaseException {
    public EmptyValueException(String message) {
        super(message);
    }
}
