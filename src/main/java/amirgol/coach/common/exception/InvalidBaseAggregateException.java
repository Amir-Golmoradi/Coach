package amirgol.coach.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidBaseAggregateException extends ParticipantBaseException {
    public InvalidBaseAggregateException(String message) {
        super(message);
    }
}
