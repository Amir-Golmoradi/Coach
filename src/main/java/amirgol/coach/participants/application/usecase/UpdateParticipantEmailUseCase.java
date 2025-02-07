package amirgol.coach.participants.application.usecase;

import amirgol.coach.common.UseCase;
import amirgol.coach.common.exception.CoachException;
import amirgol.coach.common.exception.Exceptions;
import amirgol.coach.participants.application.dto.ParticipantDTO;
import amirgol.coach.participants.application.dto.ParticipantEmailUpdateRequest;
import amirgol.coach.participants.domain.aggregate.Participant;
import amirgol.coach.participants.domain.repository.ParticipantRepository;
import amirgol.coach.participants.domain.value_object.Email;
import amirgol.coach.participants.shared.mapper.ParticipantMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateParticipantEmailUseCase extends UseCase {

    protected UpdateParticipantEmailUseCase(
            ParticipantMapper mapper,
            ParticipantRepository participantRepository) {
        super(mapper, participantRepository);
    }

    @Transactional
    public ParticipantDTO execute(ParticipantEmailUpdateRequest updateRequest, Email email) {
        // Try to retrieve the participant by the given email
        Optional<Participant> participantOpt = participantRepository.findByEmail(email);
        if (participantOpt.isEmpty()) {
            throw new CoachException(Exceptions.PARTICIPANT_NOT_FOUND, participantOpt + " not found");
        }
        Participant participant = participantOpt.get();
        // Validate that the current participant's email that is stored, matches the request
        String participantCurrentEmail = participant.getEmail().getValue();
        if (!participantCurrentEmail.equals(updateRequest.currentEmail())) {
            throw new CoachException(Exceptions.INVALID_EMAIL_FORMAT, "Provided email does not match stored email.");

        }
        // Build the new email value
        Email newEmail = Email.of(updateRequest.newEmail());

        // Call the domain method to update the email; the domain logic will also trigger its own event if needed.
        Participant result = participant.updateEmail(newEmail);
        if (result == null) {
            throw new CoachException(Exceptions.INVALID_EMAIL_UPDATE, "Failed to update email " + newEmail);
        }
        // Persist the updated participant
        participantRepository.save(participant);

        // Map the updated participant to a DTO and return
        return mapper.mapToParticipantDTO(participant);
    }
}
