package amirgol.coach.participants.domain.value_object;

import amirgol.coach.participants.domain.core.ValueObject;
import amirgol.coach.participants.domain.core.exception.value_object.EmptyValueException;
import amirgol.coach.participants.domain.core.exception.value_object.InvalidValueFormatException;
import amirgol.coach.participants.domain.core.exception.value_object.TooLongValueException;

import java.util.List;
import java.util.Objects;

/**
 * Represents the username of a participant, used for signing in to the
 * application.
 * Enforces validation rules such as length, format, and character constraints.
 */
public final class FullName extends ValueObject {

    // Constants for validation rules
    private static final int MAX_LENGTH = 50;
    private static final int MIN_LENGTH = 3;
    private static final String NAME_REGEX = "^[a-zA-Z][a-zA-Z0-9]{2,15}$";
    private static final String INVALID_NAME_MESSAGE = "Participant name must be between 3 and 16 characters long, start with a letter, and contain only letters and numbers.";

    // The actual name value
    private final String name;

    /**
     * Private constructor to enforce creation through the validate method.
     *
     * @param name The validated participant name.
     */
    private FullName(String name) {
        this.name = name;
    }

    /**
     * Validates a given name against the business rules for participant names.
     * Returns a Result object that encapsulates success or failure.
     *
     * @param valueName The name to validate.
     * @return A Result containing either a valid FullName or a
     * ParticipantException.
     */
    public static FullName of(String valueName) {
        if (valueName == null || valueName.isEmpty()) {
            throw new EmptyValueException("FullName cannot be empty.");
        }
        if (valueName.length() > MAX_LENGTH) {
            throw new TooLongValueException("FullName cannot exceed " + MAX_LENGTH + " characters.");
        }
        if (valueName.length() < MIN_LENGTH || !valueName.matches(NAME_REGEX)) {
            throw new InvalidValueFormatException(INVALID_NAME_MESSAGE);
        }
        return new FullName(valueName);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass())
            return false;
        if (!super.equals(object))
            return false;
        FullName that = (FullName) object;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }

    /**
     * Retrieves the participant name.
     *
     * @return The participant name as a string.
     */
    public String getName() {
        return name;
    }

    /**
     * Defines equality for FullName based on its value.
     *
     * @return A list containing the name, for use in ValueObject's equality checks.
     */
    @Override
    public List<Object> getEqualityComponents() {
        return List.of(name);
    }

    /**
     * Provides a string representation of the FullName for debugging purposes.
     *
     * @return A string representing the participant name.
     */
    @Override
    public String toString() {
        return "FullName{" + "name='" + name + '\'' + '}';
    }
}
