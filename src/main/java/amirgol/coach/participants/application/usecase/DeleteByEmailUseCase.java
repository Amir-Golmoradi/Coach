package amirgol.coach.participants.application.usecase;

import amirgol.coach.common.UseCase;
import amirgol.coach.participants.shared.mapper.ParticipantMapper;
import amirgol.coach.participants.domain.repository.ParticipantRepository;
import amirgol.coach.participants.domain.value_object.UserName;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DeleteByEmailUseCase extends UseCase {
    protected DeleteByEmailUseCase(ParticipantMapper mapper, ParticipantRepository participantRepository) {
        super(mapper, participantRepository);
    }

    @Transactional
    public void execute(UserName userName) {
        participantRepository.deleteByUserName(userName);
    }
}
