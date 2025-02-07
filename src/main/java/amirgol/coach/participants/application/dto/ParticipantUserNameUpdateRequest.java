package amirgol.coach.participants.application.dto;

public record ParticipantUserNameUpdateRequest(
        String currentUserName,
        String updatedUserName) {
}