package amirgol.coach.participants.domain.aggregate;

import amirgol.coach.common.BaseAggregateRoot;
import amirgol.coach.participants.domain.value_object.Email;
import amirgol.coach.participants.domain.value_object.FullName;
import amirgol.coach.participants.domain.value_object.ParticipantId;

/* Coach Security System:
    Email OTP + Magic Links + OAuth 2.0 (Google, Apple, Facebook)
 */

public class Participant extends BaseAggregateRoot<ParticipantId> {
    private final FullName name;
    private final Email email;

    protected Participant(ParticipantId id, FullName name, Email email) {
        super(id);
        this.name = name;
        this.email = email;
    }

    public static Participant of(ParticipantId id, FullName name, Email email) {
        return new Participant(id, name, email);
    }

    public Participant updateFullName(FullName updatedName) {
        /*
         * 1. Find the participant who wants to update their name by ID.
         * 2. Update the name of the participant and validate it.
         */
        return Participant.of(this.getId(), updatedName, this.email);
    }

    public Participant updateEmail(Email updatedEmail) {
        /*
         * 1. Find the participant who wants to update their email by ID.
         * 2. Update the email of the participant and validate it.
         */
        return Participant.of(this.getId(), this.name, updatedEmail);
    }

    public Email getEmail() {
        return email;
    }

    public FullName getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Participant other = (Participant) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (email == null) {
            return other.email == null;
        } else return email.equals(other.email);
    }
}
