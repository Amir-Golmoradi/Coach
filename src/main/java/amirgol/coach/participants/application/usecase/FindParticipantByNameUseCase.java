package amirgol.coach.participants.application.usecase;

import amirgol.coach.common.UseCase;
import amirgol.coach.common.exception.CoachException;
import amirgol.coach.common.exception.Exceptions;
import amirgol.coach.participants.application.dto.ParticipantDTO;
import amirgol.coach.participants.domain.repository.ParticipantRepository;
import amirgol.coach.participants.domain.value_object.UserName;
import amirgol.coach.participants.shared.mapper.ParticipantMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindParticipantByNameUseCase extends UseCase {
    protected FindParticipantByNameUseCase(ParticipantMapper mapper, ParticipantRepository participantRepository) {
        super(mapper, participantRepository);
    }

    public Optional<ParticipantDTO> execute(UserName userName) {
        if (!participantRepository.existsByUserName(userName)) {
            throw new CoachException(Exceptions.PARTICIPANT_NOT_FOUND, "Participant with username" + userName.getValue() + " not found.");
        }
        return participantRepository
                .findByUserName(userName)
                .stream()
                .map(mapper::mapToParticipantDTO)
                .findFirst();
    }
}
