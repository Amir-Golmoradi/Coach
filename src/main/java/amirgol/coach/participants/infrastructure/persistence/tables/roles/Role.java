package amirgol.coach.participants.infrastructure.persistence.tables.roles;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING) // Store ENUM as String in DB
    @Column(unique = true, nullable = false)
    private RoleType roleType;
}
