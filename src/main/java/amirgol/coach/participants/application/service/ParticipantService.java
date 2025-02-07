package amirgol.coach.participants.application.service;

import amirgol.coach.participants.application.dto.ParticipantDTO;
import amirgol.coach.participants.application.dto.ParticipantEmailUpdateRequest;
import amirgol.coach.participants.application.dto.ParticipantRegistrationRequest;
import amirgol.coach.participants.application.dto.ParticipantUserNameUpdateRequest;
import amirgol.coach.participants.domain.value_object.Email;
import amirgol.coach.participants.domain.value_object.UserName;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ParticipantService {
    Optional<List<ParticipantDTO>> findAll();

    Optional<ParticipantDTO> findByEmail(Email email);

    Optional<ParticipantDTO> findByUserName(UserName username);

    void register(ParticipantRegistrationRequest registrationRequest);

    ParticipantDTO updateParticipantEmail(ParticipantEmailUpdateRequest updateRequest, Email email);

    ParticipantDTO updateParticipantUserName(ParticipantUserNameUpdateRequest updateRequest, UserName username);

    // Must be soft delete ( user data must be kept into the database )
    void deleteByEmail(Email deletedEmail);

    // Must be soft delete ( user data must be kept into the database )
    void deleteByUserName(UserName deletedUserName);
}
