package amirgol.coach.participants.shared.dto;

/**
 * Data Transfer Object (DTO) for a participant.
 * <p>
 * It contains only the most important information about a participant, i.e. the
 * email and the user name.
 *
 * @param email    the email address of the participant. This is the primary
 *                 identifier of a participant.
 * @param userName the user name of the participant.
 */
public record ParticipantDTO(
        String email,
        String userName) {
}
