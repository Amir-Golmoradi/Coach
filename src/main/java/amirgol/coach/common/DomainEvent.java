package amirgol.coach.common;

import java.time.Instant;

public abstract class DomainEvent {

    // When an event is published,
    private final Instant occurredAt = Instant.now();
    // What is the message that will be sent to the event store
    private final String eventMessage;

    protected DomainEvent(String eventMessage) {
        this.eventMessage = eventMessage;
    }

    public String getEventMessage() {
        return eventMessage;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }
}
