package amirgol.coach.participants.application.usecase;

import amirgol.coach.common.UseCase;
import amirgol.coach.participants.application.dto.ParticipantDTO;
import amirgol.coach.participants.application.dto.mapper.ParticipantDTOMapper;
import amirgol.coach.participants.application.exception.ParticipantNotFoundException;
import amirgol.coach.participants.domain.repository.ParticipantRepository;
import amirgol.coach.participants.domain.value_object.UserName;

import java.util.Optional;

public class FindParticipantByNameUseCase extends UseCase {
    protected FindParticipantByNameUseCase(ParticipantDTOMapper mapper, ParticipantRepository participantRepository) {
        super(mapper, participantRepository);
    }

    public Optional<ParticipantDTO> execute(UserName userName) {
        if (!participantRepository.existsByUserName(userName)) {
            throw new ParticipantNotFoundException(STR."Participant with username '\{userName.getValue()}' not found.");
        }
        return participantRepository.findByUserName(userName).stream().map(mapper).findFirst();
    }
}
