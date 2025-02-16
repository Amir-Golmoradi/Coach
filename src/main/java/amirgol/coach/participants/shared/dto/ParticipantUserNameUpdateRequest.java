package amirgol.coach.participants.shared.dto;

public record ParticipantUserNameUpdateRequest(
        String currentUserName,
        String updatedUserName) {
}