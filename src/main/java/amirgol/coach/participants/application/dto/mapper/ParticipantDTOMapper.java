package amirgol.coach.participants.application.dto.mapper;

import amirgol.coach.participants.application.dto.ParticipantDTO;
import amirgol.coach.participants.domain.aggregate.Participant;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ParticipantDTOMapper implements Function<Participant, ParticipantDTO> {

    @Override
    public ParticipantDTO apply(Participant participant) {
        return new ParticipantDTO(participant.getEmail().getValue(), participant.getName().getValue());
    }
}
