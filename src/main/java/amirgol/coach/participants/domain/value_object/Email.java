package amirgol.coach.participants.domain.value_object;

import amirgol.coach.participants.domain.core.ValueObject;
import amirgol.coach.participants.domain.core.exception.value_object.EmptyValueException;
import amirgol.coach.participants.domain.core.exception.value_object.InvalidValueFormatException;
import amirgol.coach.participants.domain.core.exception.value_object.TooLongValueException;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Email extends ValueObject {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", Pattern.CASE_INSENSITIVE);

    private static final int EMAIL_MIN_LENGTH = 3;
    private static final int EMAIL_MAX_LENGTH = 50;
    private static final String INVALID_EMAIL_MESSAGE = "Email must be between 3 and 50 characters long, contain only letters, numbers, and special characters (@, ., _, -), and follow standard email address formatting.";

    private final String participantEmail;

    private Email(String participantEmail) {
        this.participantEmail = participantEmail;
    }

    public static Email of(String email) {
        if (email.length() >= EMAIL_MAX_LENGTH) {
            throw new TooLongValueException("Email cannot exceed " + EMAIL_MAX_LENGTH + " characters.");
        }
        if (email.isBlank()) {
            throw new EmptyValueException("Email cannot be empty.");
        }

        if (email.length() < EMAIL_MIN_LENGTH || !EMAIL_PATTERN.matcher(email.trim().toLowerCase()).matches()) {
            throw new InvalidValueFormatException(INVALID_EMAIL_MESSAGE);
        }
        return new Email(email);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Email email1 = (Email) object;
        return Objects.equals(participantEmail, email1.participantEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), participantEmail);
    }

    @Override
    public List<Object> getEqualityComponents() {
        return List.of(participantEmail);
    }
}
