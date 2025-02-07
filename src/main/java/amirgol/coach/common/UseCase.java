package amirgol.coach.common;

import amirgol.coach.participants.domain.repository.ParticipantRepository;
import amirgol.coach.participants.shared.mapper.ParticipantMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class UseCase {
    public final ParticipantMapper mapper;
    public final ParticipantRepository participantRepository;
}
