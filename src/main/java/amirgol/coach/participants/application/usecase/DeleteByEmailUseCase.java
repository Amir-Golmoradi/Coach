package amirgol.coach.participants.application.usecase;

import amirgol.coach.common.UseCase;
import amirgol.coach.participants.application.dto.mapper.ParticipantDTOMapper;
import amirgol.coach.participants.domain.repository.ParticipantRepository;
import amirgol.coach.participants.domain.value_object.Email;
import amirgol.coach.participants.domain.value_object.UserName;

public class DeleteByEmailUseCase extends UseCase {
    protected DeleteByEmailUseCase(ParticipantDTOMapper mapper, ParticipantRepository participantRepository) {
        super(mapper, participantRepository);
    }

    public void execute(UserName userName) {
        participantRepository.deleteByUserName(userName);
    }
}
