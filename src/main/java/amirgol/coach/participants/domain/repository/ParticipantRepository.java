package amirgol.coach.participants.domain.repository;

import amirgol.coach.participants.domain.aggregate.Participant;
import amirgol.coach.participants.domain.value_object.Email;
import amirgol.coach.participants.domain.value_object.UserName;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link Participant} entities.
 * Provides methods for retrieving, checking existence, inserting, and deleting participants.
 */
public interface ParticipantRepository {

    /**
     * Retrieves all participants.
     *
     * @return An {@link Optional} containing a list of all participants, or empty if none exist.
     */
    Optional<List<Participant>> findAll();

    /**
     * Finds a participant by email.
     *
     * @param email The email of the participant.
     * @return An {@link Optional} containing the participant if found, otherwise empty.
     */
    Optional<Participant> findByEmail(Email email);

    Optional<Participant> findByUserName(UserName userName);

    /**
     * Checks whether a participant exists by email.
     *
     * @param email The email to check.
     * @return {@code true} if a participant exists, otherwise {@code false}.
     */
    boolean existsByEmail(Email email);

    boolean existsByUserName(UserName userName);

    /**
     * Saves a participant to the repository.
     *
     * @param participant The participant to save.
     */
    void save(Participant participant);


    /**
     * Deletes a participant by email.
     *
     * @param email The email of the participant to delete.
     */
    void deleteByEmail(Email email);

    void deleteByUserName(UserName userName);
}
