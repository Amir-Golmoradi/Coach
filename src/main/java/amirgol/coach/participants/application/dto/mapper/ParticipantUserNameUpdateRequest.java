package amirgol.coach.participants.application.dto.mapper;

public record ParticipantUserNameUpdateRequest(
                String currentUserName,
                String updatedUserName) {
}