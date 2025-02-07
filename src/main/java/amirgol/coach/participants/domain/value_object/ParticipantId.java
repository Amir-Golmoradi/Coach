package amirgol.coach.participants.domain.value_object;

import amirgol.coach.common.exception.CoachException;
import amirgol.coach.common.exception.Exceptions;
import amirgol.coach.participants.domain.core.ValueObject;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
public final class ParticipantId extends ValueObject {
    private final UUID id;

    public ParticipantId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Bad Request! ID must be a non-null UUID.");
        }
        this.id = id;
    }

    /**
     * Factory method to validate and create a ParticipantId.
     *
     * @param participantId UUID value representing the Participant ID.
     * @return A valid ParticipantId.
     */
    public static ParticipantId of(UUID participantId) {
        if (participantId == null) {
            throw new CoachException(Exceptions.INVALID_VALUE_FORMAT, "Bad Request! ID must be a non-null UUID.");
        }
        return new ParticipantId(participantId);
    }

    /**
     * Overloaded factory method to create a ParticipantId from a String representation.
     *
     * @param idAsString String representation of the UUID.
     * @return A valid ParticipantId.
     */
    public static ParticipantId fromString(String idAsString) {
        try {
            UUID uuid = UUID.fromString(idAsString);
            return new ParticipantId(uuid);
        } catch (CoachException ex) {
            throw new CoachException(Exceptions.INVALID_VALUE_FORMAT, "Bad Request! Invalid UUID format: " + idAsString);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        ParticipantId that = (ParticipantId) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public List<Object> getEqualityComponents() {
        return List.of(id);
    }
}
