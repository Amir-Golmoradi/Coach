package amirgol.coach.participants.domain.core.exception.value_object;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidValueFormatException extends RuntimeException {
    public InvalidValueFormatException(String message) {
        super(message);
    }
}
