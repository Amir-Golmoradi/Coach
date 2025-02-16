package amirgol.coach.participants.shared.dto;

public record ParticipantEmailUpdateRequest(
        String currentEmail,
        String newEmail) {
}
