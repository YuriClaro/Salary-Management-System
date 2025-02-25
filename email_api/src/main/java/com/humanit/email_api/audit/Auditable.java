package com.humanit.email_api.audit;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable implements Serializable {
    @CreatedBy
    @NotEmpty
    private String createdBy;

    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createdAt;

    @LastModifiedBy
    @NotEmpty
    private String lastModifiedBy;

    @LastModifiedDate
    @UpdateTimestamp
    private LocalDateTime lastModifiedAt;
}
