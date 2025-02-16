package amirgol.coach.participants.shared.mapper;

import amirgol.coach.participants.shared.dto.ParticipantDTO;
import amirgol.coach.participants.domain.aggregate.Participant;
import amirgol.coach.participants.infrastructure.persistence.tables.ParticipantTable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {

    ParticipantDTO mapToParticipantDTO(Participant participant);

    ParticipantTable mapToParticipantAggregate(Participant participant);

    Participant mapToParticipantTable(ParticipantTable participantTable);
}






