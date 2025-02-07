package amirgol.coach.participants.application.usecase;

import amirgol.coach.common.UseCase;
import amirgol.coach.common.exception.CoachException;
import amirgol.coach.common.exception.Exceptions;
import amirgol.coach.participants.application.dto.ParticipantDTO;
import amirgol.coach.participants.domain.repository.ParticipantRepository;
import amirgol.coach.participants.domain.value_object.Email;
import amirgol.coach.participants.shared.mapper.ParticipantMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class FindParticipantByEmailUseCase extends UseCase {

    FindParticipantByEmailUseCase(ParticipantMapper mapper, ParticipantRepository participantRepository) {
        super(mapper, participantRepository);
    }

    public Optional<ParticipantDTO> execute(Email email) {
        if (!participantRepository.existsByEmail(email)) {
            throw new CoachException(Exceptions.PARTICIPANT_NOT_FOUND, "Participant with email" + email.getValue() + "not found");
        }
        return participantRepository.findByEmail(email)
                .stream()
                .map(mapper::mapToParticipantDTO)
                .findFirst();
    }

}
