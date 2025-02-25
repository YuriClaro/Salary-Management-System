package com.humanit.email_api.model;

import com.humanit.email_api.audit.Auditable;
import com.humanit.email_api.enumerator.EmailStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Audited
@NoArgsConstructor
@AllArgsConstructor
public class Email extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String receiver;

    @Column(nullable = false)
    private String subject;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    private byte[] attachment;

    @Column(nullable = false)
    private LocalDateTime sendDateEmail;

    @Enumerated(EnumType.STRING)
    private EmailStatus status;

    @Version
    private Long version;

    public Email(String sender, String receiver, String subject, String text, byte[] attachment,
                 LocalDateTime sendDateEmail, EmailStatus status, Long version) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.text = text;
        this.attachment = attachment;
        this.sendDateEmail = sendDateEmail;
        this.status = status;
        this.version = version;
    }
}
