package amirgol.coach.participants.infrastructure.persistence.repositories;

import amirgol.coach.common.exception.CoachException;
import amirgol.coach.common.exception.Exceptions;
import amirgol.coach.participants.domain.aggregate.Participant;
import amirgol.coach.participants.domain.repository.ParticipantRepository;
import amirgol.coach.participants.domain.value_object.Email;
import amirgol.coach.participants.domain.value_object.UserName;
import amirgol.coach.participants.infrastructure.persistence.tables.ParticipantTable;
import amirgol.coach.participants.shared.mapper.ParticipantMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ParticipantDataAccessService implements ParticipantRepository {
    private final ParticipantMapper mapper;
    private final ParticipantTableRepository repository;

    @Override
    @Transactional
    public Optional<List<Participant>> findAll() {
        return Optional.of(repository.findAll()
                .stream().map(mapper::mapToParticipantTable).toList()
        );
    }

    @Override
    @Transactional
    public Optional<Participant> findByEmail(Email email) {
        if (!existsByEmail(email)) {
            throw new CoachException(Exceptions.PARTICIPANT_NOT_FOUND, "No Email found");
        }
        return Optional.of(repository.findByEmail(email.getValue()));
    }

    @Override
    @Transactional
    public Optional<Participant> findByUserName(UserName userName) {
        if (!existsByUserName(userName)) {
            throw new CoachException(Exceptions.PARTICIPANT_NOT_FOUND, "No Username found");
        }
        return Optional.of(repository.findByUserName(userName.getValue()));
    }

    @Override
    public boolean existsByEmail(Email email) {
        return repository.existsByEmail(email.getValue());
    }

    @Override
    public boolean existsByUserName(UserName userName) {
        return repository.existsByUserName(userName.getValue());
    }

    @Override
    @Transactional
    public void save(Participant participant) {
        if (findAll().isEmpty()) {
            throw new IllegalArgumentException("No one found");
        }
        ParticipantTable persistedParticipant = mapper.mapToParticipantAggregate(participant);
        repository.save(persistedParticipant);
    }

    @Override
    public void deleteByEmail(Email email) {
        repository.deleteByEmail(email.getValue());
    }

    @Override
    public void deleteByUserName(UserName userName) {
        repository.deleteByName(userName.getValue());
    }
}
