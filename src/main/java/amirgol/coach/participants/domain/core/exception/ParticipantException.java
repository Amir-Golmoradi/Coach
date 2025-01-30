package amirgol.coach.participants.domain.core.exception;

public class ParticipantException extends RuntimeException {

    private final ErrorType errorType;
    private final String message;

    public ParticipantException(ErrorType errorType, String message) {
        this.errorType = errorType;
        this.message = message;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
