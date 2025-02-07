package amirgol.coach.participants.application.usecase;

import amirgol.coach.common.UseCase;
import amirgol.coach.participants.application.dto.ParticipantRegistrationRequest;
import amirgol.coach.participants.shared.mapper.ParticipantMapper;
import amirgol.coach.participants.domain.aggregate.Participant;
import amirgol.coach.participants.domain.repository.ParticipantRepository;
import amirgol.coach.participants.domain.value_object.Email;
import amirgol.coach.participants.domain.value_object.ParticipantId;
import amirgol.coach.participants.domain.value_object.UserName;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateParticipantUseCase extends UseCase {
    // Add DomainEvent for creating new participant
    protected CreateParticipantUseCase(
            ParticipantMapper mapper,
            ParticipantRepository participantRepository
    ) {
        super(mapper, participantRepository);
    }

    @Transactional
    public void execute(ParticipantRegistrationRequest registrationRequest) {
        Participant participant = Participant.of(
                ParticipantId.of(registrationRequest.id()),
                UserName.of(registrationRequest.userName()),
                Email.of(registrationRequest.email())
        );
        participantRepository.save(participant);
    }
}
