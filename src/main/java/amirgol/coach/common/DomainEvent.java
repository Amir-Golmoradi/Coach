package amirgol.coach.common;

import lombok.Getter;

import java.time.Instant;

@Getter
public abstract class DomainEvent {

    // When an event is published,
    private final Instant occurredAt = Instant.now();
    // What is the message that will be sent to the event store
    private final String eventMessage;

    protected DomainEvent(String eventMessage) {
        this.eventMessage = eventMessage;
    }

}
