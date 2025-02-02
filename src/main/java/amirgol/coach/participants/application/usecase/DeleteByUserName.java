package amirgol.coach.participants.application.usecase;

import amirgol.coach.common.UseCase;
import amirgol.coach.participants.application.dto.mapper.ParticipantDTOMapper;
import amirgol.coach.participants.domain.repository.ParticipantRepository;
import amirgol.coach.participants.domain.value_object.Email;

public class DeleteByUserName extends UseCase {
    protected DeleteByUserName(ParticipantDTOMapper mapper, ParticipantRepository participantRepository) {
        super(mapper, participantRepository);
    }

    public void execute(Email deletedEmail) {
        participantRepository.deleteByEmail(deletedEmail);
    }
}
