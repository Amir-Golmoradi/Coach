package amirgol.coach.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDomainEventException extends RuntimeException {

    public InvalidDomainEventException(String message) {
        super(message);
    }
}
