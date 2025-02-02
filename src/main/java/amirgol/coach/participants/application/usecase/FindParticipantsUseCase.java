package amirgol.coach.participants.application.usecase;

import amirgol.coach.common.UseCase;
import amirgol.coach.participants.application.dto.ParticipantDTO;
import amirgol.coach.participants.application.dto.mapper.ParticipantDTOMapper;
import amirgol.coach.participants.application.exception.ParticipantNotFoundException;
import amirgol.coach.participants.domain.repository.ParticipantRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FindParticipantsUseCase extends UseCase {
    public FindParticipantsUseCase(ParticipantDTOMapper mapper, ParticipantRepository participantRepository) {
        super(mapper, participantRepository);
    }

    public Optional<List<ParticipantDTO>> execute() {
        return participantRepository.findAll()
                .flatMap(participants -> {
                    if (participants.isEmpty()) {
                        throw new ParticipantNotFoundException("No participants found.");
                    } else {
                        return Optional.of(participants
                                .stream()
                                .map(mapper)
                                .collect(Collectors.toList()));
                    }
                });
    }
}
