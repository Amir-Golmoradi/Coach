package amirgol.coach.participants.shared.dto;

import java.util.UUID;

public record ParticipantRegistrationRequest(
        UUID id,
        String userName,
        String email
) {

}

