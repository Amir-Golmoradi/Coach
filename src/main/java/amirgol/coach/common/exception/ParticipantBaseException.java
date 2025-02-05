package amirgol.coach.common.exception;

public class ParticipantBaseException extends RuntimeException {
    public ParticipantBaseException(String message) {
        super(message);
    }

    public ParticipantBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
