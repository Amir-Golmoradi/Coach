package amirgol.coach.participants.domain.value_object;

import amirgol.coach.common.ValueObject;
import amirgol.coach.common.exception.CoachException;
import amirgol.coach.common.exception.Exceptions;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents the username create a participant, used for signing in to the
 * application.
 * Enforces validation rules such as length, format, and character constraints.
 */
@Getter
public final class UserName extends ValueObject {

    // Constants for validation rules
    private static final int MAX_LENGTH = 50;
    private static final int MIN_LENGTH = 3;
    private static final Pattern NAME_REGEX = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]{2,15}$");
    private static final String INVALID_NAME_MESSAGE = "Username must be between 3 and 50 characters long, start with a letter, and contain only letters and numbers.";
    /**
     * -- GETTER --
     * Retrieves the participant name.
     *
     * @return The participant name as a string.
     */
    // The actual name value
    private final String value;

    /**
     * Private constructor to enforce creation through the validate method.
     *
     * @param value The validated participant name.
     */
    public UserName(String value) {
        this.value = value;
    }

    /**
     * Validates a given name against the business rules for participant names.
     * Returns a Result object that encapsulates success or failure.
     *
     * @param valueName The name to validate.
     * @return A Result containing either a valid FullName or a
     * ParticipantException.
     */
    public static UserName of(String valueName) {
        if (valueName == null || valueName.isEmpty()) {
            throw new CoachException(Exceptions.EMPTY_NAME, "Username cannot be empty.");
        }
        if (valueName.length() > MAX_LENGTH) {
            throw new CoachException(Exceptions.TOO_LONG_NAME, "Username cannot exceed" + MAX_LENGTH + "characters.");
        }
        if (valueName.length() < MIN_LENGTH || !NAME_REGEX.matcher(valueName).matches()) {
            throw new CoachException(Exceptions.INVALID_NAME_FORMAT, INVALID_NAME_MESSAGE);
        }
        return new UserName(valueName);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass())
            return false;
        if (!super.equals(object))
            return false;
        UserName username = (UserName) object;
        return Objects.equals(getValue(), username.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getValue());
    }

    /**
     * Defines equality for FullName based on its value.
     *
     * @return A list containing the name, for use in ValueObject's equality checks.
     */
    @Override
    public List<Object> getEqualityComponents() {
        return List.of(value);
    }

    /**
     * Provides a string representation create the username for debugging purposes.
     *
     * @return A string representing the participant username.
     */
    @Override
    public String toString() {
        return "Username{" + "name='" + value + '\'' + '}';
    }
}
