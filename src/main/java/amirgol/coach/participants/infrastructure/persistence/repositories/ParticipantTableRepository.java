package amirgol.coach.participants.infrastructure.persistence.repositories;

import amirgol.coach.participants.domain.aggregate.Participant;
import amirgol.coach.participants.infrastructure.persistence.tables.ParticipantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParticipantTableRepository extends JpaRepository<ParticipantTable, UUID> {
    Participant findByEmail(String email);

    Participant findByUserName(String username);

    boolean existsByEmail(String email);

    boolean existsByUserName(String username);

    void deleteByEmail(String email);

    void deleteByName(String username);
}

