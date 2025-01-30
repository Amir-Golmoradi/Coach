package amirgol.coach.participants.domain.value_object;

import java.util.List;
import java.util.Objects;

import com.github.sviperll.result4j.Result;

import amirgol.coach.participants.domain.core.ValueObject;
import amirgol.coach.participants.domain.core.exception.ErrorType;
import amirgol.coach.participants.domain.core.exception.ParticipantException;

public final class ParticipantId extends ValueObject {
    private final Long id;

    public ParticipantId(Long id) {
        this.id = id;
    }

    /**
     * Factory method to validate and create a ParticipantId.
     *
     * @param participantId Long value representing the Participant ID.
     * @return Result containing either a valid ParticipantId or an error message.
     */
    public static Result<ParticipantId, ParticipantException> isValidId(Long participantId) {
        if (participantId == null || participantId <= 0) {
            return Result.error(new ParticipantException(
                    ErrorType.BAD_REQUEST, "Bad Request! ID must be a positive non-null value."));
        }
        return Result.success(new ParticipantId(participantId));
    }

    public Long getId() {
        return id;
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