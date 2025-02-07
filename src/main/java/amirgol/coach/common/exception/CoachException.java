package amirgol.coach.common.exception;

import lombok.Getter;

@Getter
public class CoachException extends RuntimeException {
    private final Exceptions exceptions;

    public CoachException(Exceptions exceptions) {
        super(exceptions.getErrorText());
        this.exceptions = exceptions;
    }

    public CoachException(Exceptions exception, String customMessage) {
        super(customMessage);
        this.exceptions = exception;
    }

    public CoachException(Exceptions exception, Throwable cause) {
        super(exception.getErrorText(), cause);
        this.exceptions = exception;
    }
}