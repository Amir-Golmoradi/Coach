package amirgol.coach.participants.application.usecase;

import amirgol.coach.common.UseCase;
import amirgol.coach.participants.application.dto.ParticipantDTO;
import amirgol.coach.participants.application.dto.mapper.ParticipantDTOMapper;
import amirgol.coach.participants.application.dto.mapper.ParticipantUserNameUpdateRequest;
import amirgol.coach.participants.application.exception.ParticipantNotFoundException;
import amirgol.coach.participants.domain.aggregate.Participant;
import amirgol.coach.participants.domain.core.exception.events.InvalidEmailException;
import amirgol.coach.participants.domain.core.exception.events.InvalidUserNameException;
import amirgol.coach.participants.domain.core.exception.value_object.InvalidValueFormatException;
import amirgol.coach.participants.domain.repository.ParticipantRepository;
import amirgol.coach.participants.domain.value_object.UserName;

import java.util.Optional;

public class UpdateParticipantUserNameUseCase extends UseCase {
    protected UpdateParticipantUserNameUseCase(ParticipantDTOMapper mapper, ParticipantRepository participantRepository) {
        super(mapper, participantRepository);
    }

    public ParticipantDTO execute(ParticipantUserNameUpdateRequest userNameUpdateRequest, UserName userName) {
        // Try to retrieve the participant by the given username
        Optional<Participant> participantOpt = participantRepository.findByUserName(userName);
        if (participantOpt.isEmpty()) {
            throw new ParticipantNotFoundException(STR."\{participantOpt} not found");
        }
        Participant participant = participantOpt.get();
        // Validate that the current username stored in the participant matches the request
        // If the participant's current username does not match the requested username, reject the change request.
        String participantCurrentName = participant.getName().getValue();
        if (!participantCurrentName.equals(userNameUpdateRequest.currentUserName())) {
            throw new InvalidValueFormatException(STR."The\{participantCurrentName} username does not match the participant's stored username.");
        }
        // Build new UserName value.
        UserName name = UserName.of(userNameUpdateRequest.updatedUserName());

        // Call the domain method to update the username; the domain logic will also trigger its own event if needed.
        Participant result = participant.updateUserName(name);
        if (result == null) {
            throw new InvalidUserNameException("Failed to update username");
        }
        participantRepository.save(participant);

        return mapper.apply(participant);
    }
}
