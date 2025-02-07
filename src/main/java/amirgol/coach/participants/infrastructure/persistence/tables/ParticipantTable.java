package amirgol.coach.participants.infrastructure.persistence.tables;

import amirgol.coach.participants.infrastructure.persistence.tables.roles.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "participants", uniqueConstraints = @UniqueConstraint(columnNames = "email", name = "participant_unique_email"))
public class ParticipantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "participant_id", updatable = false, nullable = false)
    private UUID id;


    /**
     * The email field is encrypted before storing in the database.
     */
    @Column(nullable = false, name = "email") // Cannot be Null at Database Level
    @NotNull(message = "Email cannot be null") // Cannot be Null at Application Level
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email format is invalid")
    private String email;

    @Column(nullable = false, name = "username") // Cannot be Null at Database Level
    @NotNull(message = "UserName cannot be null") // Cannot be Null at Application Level
    @NotEmpty(message = "UserName cannot be empty")
    private String username;

    /**
     * Indicates whether the email has been verified or not.
     */
    @Column(nullable = false, name = "is_email_verified")
    private boolean isEmailVerified = false;

    /**
     * The timestamp when the email was verified.
     */
    @Column(nullable = false, name = "when_email_verified")
    private LocalDateTime whenEmailVerified;

    // Security metadata fields:
    @Column(name = "failed_login_attempts", nullable = false)
    private int failedLoginAttempts;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;


    // Timestamps for audit purposes.
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "participant_roles",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}

