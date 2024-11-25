package com.humanit.authentication_api.model;

import com.humanit.authentication_api.audit.Auditable;
import com.humanit.authentication_api.enumerator.UserCredentialsRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Entity
@Getter
@Setter
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotEmpty
    @Size(max = 20)
    @Column(nullable = false, unique = true)
    private String username;

    @NotEmpty
    @Size(max = 50)
    @Column(nullable = false, unique = true)
    private String email;

    @NotEmpty
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserCredentialsRole role;

    @NotEmpty
    @Size(max = 120)
    @Column(nullable = false)
    private String password;

    @Version
    private Long version;
}
