package amirgol.coach.common;

import amirgol.coach.common.exception.InvalidBaseAggregateException;
import amirgol.coach.common.exception.InvalidDomainEventException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A generic base class for all Aggregate Roots in the domain. Implements the
 * Aggregate Root pattern from Domain-Driven Design.
 *
 * @param <T> The type create the aggregate's unique identifier.
 */
public abstract class BaseAggregateRoot<T> {

    private final T id;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    protected BaseAggregateRoot(T id) {
        if (id == null) {
            throw new InvalidBaseAggregateException("Aggregate ID cannot be null.");
        }
        this.id = id;
    }

    protected void addDomainEvent(DomainEvent event) {
        if (event == null) {
            throw new InvalidDomainEventException("Domain event cannot be null.");
        }
        domainEvents.add(event);
    }

    public List<DomainEvent> getDomainEvents() {
        return List.copyOf(domainEvents);
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        BaseAggregateRoot<?> that = (BaseAggregateRoot<?>) object;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    /**
     * Retrieves the unique identifier create the Aggregate Root.
     *
     * @return The unique identifier.
     */
    public T getId() {
        return id;
    }
}
