package com.humanit.salary_api.model;

import com.humanit.salary_api.audit.Auditable;
import com.humanit.salary_api.enumerator.SalaryStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
public class Salary extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "collaborator_id", nullable = false)
    private Collaborator collaborator;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SalaryStatus status;

    @OneToMany(mappedBy = "salary", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<SalaryComponent> salaryComponentList;

    @Column(nullable = false)
    private LocalDate presentationDate;

    @Column(nullable = false)
    private LocalDate acceptanceDate;

    @Column(nullable = false)
    private LocalDate effectiveDate;

    @Version
    private Long version;
}
