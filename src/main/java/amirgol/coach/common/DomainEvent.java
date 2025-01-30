package amirgol.coach.common;

import java.time.Instant;

public abstract class DomainEvent {

  protected DomainEvent(Instant occurredAt, EventType eventType) {
    this.eventType = eventType;
    this.occurredAt = occurredAt;
  }

  // When an event is published,
  private final Instant occurredAt;

  public Instant getOccurredAt() {
    return occurredAt;
  }

  // What is the message that will be sent to the event store
  private final EventType eventType;

  public EventType getEventType() {
    return eventType;
  }
}
