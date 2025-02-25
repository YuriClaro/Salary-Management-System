package com.humanit.authentication_api.model;

import com.humanit.authentication_api.audit.Auditable;
import com.humanit.authentication_api.enumerator.UserCredentialsRole;
import com.humanit.authentication_api.enumerator.UserCredentialsStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.UUID;

import static com.humanit.authentication_api.enumerator.UserCredentialsStatus.LOGGED_OUT;

@Entity
@Getter
@Setter
@Audited
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

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserCredentialsRole role;

    @NotEmpty
    @Size(max = 120)
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserCredentialsStatus status = LOGGED_OUT;

    @Version
    private Long version;
}
