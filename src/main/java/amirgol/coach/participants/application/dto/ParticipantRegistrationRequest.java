package amirgol.coach.participants.application.dto;

import java.util.UUID;

public record ParticipantRegistrationRequest(
        UUID id,
        String userName,
        String email
) {

}

