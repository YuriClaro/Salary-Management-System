package com.humanit.salary_api.model;

import com.humanit.salary_api.audit.Auditable;
import com.humanit.salary_api.enumerator.ComponentType;
import com.humanit.salary_api.enumerator.CoverFlex;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryComponent extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "salary_id", nullable = false)
    private Salary salary;

    @Enumerated(EnumType.STRING)
    private ComponentType type;

    @Enumerated(EnumType.STRING)
    private CoverFlex coverFlex;

    private BigDecimal value;

    @Version
    private Long version;
}
