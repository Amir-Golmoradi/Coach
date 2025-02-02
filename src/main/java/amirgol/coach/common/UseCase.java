package amirgol.coach.common;

import amirgol.coach.participants.application.dto.mapper.ParticipantDTOMapper;
import amirgol.coach.participants.domain.repository.ParticipantRepository;

public abstract class UseCase {
    public final ParticipantDTOMapper mapper;
    public final ParticipantRepository participantRepository;

    protected UseCase(ParticipantDTOMapper mapper, ParticipantRepository participantRepository) {
        this.mapper = mapper;
        this.participantRepository = participantRepository;
    }
}
