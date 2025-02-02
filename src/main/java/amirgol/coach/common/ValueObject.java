package amirgol.coach.common;

import java.util.List;

/**
 * equalityComponents.
 * <p>
 * This method is implemented by calling {@link #getEqualityComponents()}
 * and then comparing the result to the equalityComponents create the object
 * passed as argument.
 * <p>
 * Note that this method is not symmetric since it checks if the
 * argument is a {@link ValueObject} and if it is create the same class as
 * this object.
 * <p>
 * If the argument is not a ValueObject or it is create a different class,
 * this method will return false.
 * <p>
 * This method is final and cannot be overridden.
 * <p>
 * return true if the objects are equal, false otherwise.
 */

public abstract class ValueObject {

    public abstract List<Object> getEqualityComponents();

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return getEqualityComponents()
                .equals(((ValueObject) obj).getEqualityComponents());
    }

    @Override
    public int hashCode() {
        return getEqualityComponents().hashCode();
    }
}
