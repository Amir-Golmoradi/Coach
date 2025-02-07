package amirgol.coach.participants.application.usecase;

import amirgol.coach.common.UseCase;
import amirgol.coach.common.exception.CoachException;
import amirgol.coach.common.exception.Exceptions;
import amirgol.coach.participants.application.dto.ParticipantDTO;
import amirgol.coach.participants.application.dto.ParticipantUserNameUpdateRequest;
import amirgol.coach.participants.domain.aggregate.Participant;
import amirgol.coach.participants.domain.repository.ParticipantRepository;
import amirgol.coach.participants.domain.value_object.UserName;
import amirgol.coach.participants.shared.mapper.ParticipantMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateParticipantUserNameUseCase extends UseCase {
    protected UpdateParticipantUserNameUseCase(ParticipantMapper mapper, ParticipantRepository participantRepository) {
        super(mapper, participantRepository);
    }

    @Transactional
    public ParticipantDTO execute(ParticipantUserNameUpdateRequest userNameUpdateRequest, UserName userName) {
        // Try to retrieve the participant by the given username
        Optional<Participant> participantOpt = participantRepository.findByUserName(userName);
        if (participantOpt.isEmpty()) {
            throw new CoachException(Exceptions.PARTICIPANT_NOT_FOUND, participantOpt + " not found");
        }
        Participant participant = participantOpt.get();
        // Validate that the current username stored in the participant matches the request
        // If the participant's current username does not match the requested username, reject the change request.
        String participantCurrentName = participant.getName().getValue();
        if (!participantCurrentName.equals(userNameUpdateRequest.currentUserName())) {
            throw new CoachException(Exceptions.INVALID_NAME_FORMAT, participantCurrentName + " username does not match the participant's stored username.");
        }
        // Build new UserName value.
        UserName name = UserName.of(userNameUpdateRequest.updatedUserName());

        // Call the domain method to update the username; the domain logic will also trigger its own event if needed.
        Participant result = participant.updateUserName(name);
        if (result == null) {
            throw new CoachException(Exceptions.INVALID_NAME_FORMAT, participantCurrentName + " username does not match the participant's stored username.");

        }
        participantRepository.save(participant);

        return mapper.mapToParticipantDTO(participant);
    }
}
