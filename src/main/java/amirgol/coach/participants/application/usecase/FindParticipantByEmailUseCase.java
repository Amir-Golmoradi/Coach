package amirgol.coach.participants.application.usecase;

import amirgol.coach.common.UseCase;
import amirgol.coach.participants.application.dto.ParticipantDTO;
import amirgol.coach.participants.application.dto.mapper.ParticipantDTOMapper;
import amirgol.coach.participants.application.exception.ParticipantNotFoundException;
import amirgol.coach.participants.domain.repository.ParticipantRepository;
import amirgol.coach.participants.domain.value_object.Email;

import java.util.Optional;


public class FindParticipantByEmailUseCase extends UseCase {

    FindParticipantByEmailUseCase(ParticipantDTOMapper mapper, ParticipantRepository participantRepository) {
        super(mapper, participantRepository);
    }

    public Optional<ParticipantDTO> execute(Email email) {
        if (!participantRepository.existsByEmail(email)) {
            throw new ParticipantNotFoundException(STR."Participant with email '\{email.getValue()}' not found.");
        }
        return participantRepository.findByEmail(email)
                .stream()
                .map(mapper)
                .findFirst();
    }

}
