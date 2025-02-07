package amirgol.coach.participants.domain.core;

import amirgol.coach.common.exception.CoachException;
import amirgol.coach.common.exception.Exceptions;

import java.util.List;

/**
 * equalityComponents.
 * <p>
 * This method is implemented by calling {@link #getEqualityComponents()}
 * and then comparing the result to the equalityComponents of the object
 * passed as argument.
 * <p>
 * Note that this method is not symmetric since it checks if the
 * argument is a {@link ValueObject} and if it is of the same class as
 * this object.
 * <p>
 * If the argument is not a ValueObject, or it is of a different class,
 * this method will return false.
 * <p>
 * This method is final and cannot be overridden.
 * <p>
 * return true if the objects are equal, false otherwise.
 */

public abstract class ValueObject {
    protected ValueObject() {
        List<Object> components = getEqualityComponents();

        // Checks for Immutability equality between two components.
        if (components.stream().anyMatch(component -> !isImmutable(component))) {
            throw new CoachException(Exceptions.INVALID_VALUE_FORMAT, "All equality components must be immutable.");

        }
    }

    public abstract List<Object> getEqualityComponents();

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return getEqualityComponents()
                .equals(((ValueObject) obj).getEqualityComponents());
    }

    private boolean isImmutable(Object object) {
        // Check if the object is a known immutable type (e.g., primitives, String, etc.)
        return object == null || object.getClass().isPrimitive() || object instanceof String;
    }


    @Override
    public int hashCode() {
        return getEqualityComponents().hashCode();
    }
}
