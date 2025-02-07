package amirgol.coach.common.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        int errorCode,
        String errorMessage,
        String timestamp,
        String path
) {
    public ErrorResponse(CoachException exception, String path) {
        this(
                exception.getExceptions().getErrorStatusCode(),
                exception.getMessage(),
                LocalDateTime.now().toString(),
                path
        );
    }
}
