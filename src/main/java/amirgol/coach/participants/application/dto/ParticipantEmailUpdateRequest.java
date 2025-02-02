package amirgol.coach.participants.application.dto;

public record ParticipantEmailUpdateRequest(
        String currentEmail,
        String newEmail) {
}
