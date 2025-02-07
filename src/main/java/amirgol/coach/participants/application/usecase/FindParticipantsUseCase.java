package amirgol.coach.participants.application.usecase;

import amirgol.coach.common.UseCase;
import amirgol.coach.common.exception.CoachException;
import amirgol.coach.common.exception.Exceptions;
import amirgol.coach.participants.application.dto.ParticipantDTO;
import amirgol.coach.participants.domain.repository.ParticipantRepository;
import amirgol.coach.participants.shared.mapper.ParticipantMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FindParticipantsUseCase extends UseCase {
    public FindParticipantsUseCase(ParticipantMapper mapper, ParticipantRepository participantRepository) {
        super(mapper, participantRepository);
    }

    public Optional<List<ParticipantDTO>> execute() {
        return participantRepository.findAll()
                .flatMap(participants -> {
                    if (participants.isEmpty()) {
                        throw new CoachException(Exceptions.PARTICIPANT_NOT_FOUND, "No participants found.");
                    } else {
                        return Optional.of(participants
                                .stream()
                                .map(mapper::mapToParticipantDTO)
                                .collect(Collectors.toList()));
                    }
                });
    }
}
