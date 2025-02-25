package com.humanit.salary_api.repository;

import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.model.Collaborator;
import com.humanit.salary_api.model.Salary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, UUID>, JpaSpecificationExecutor<Salary> {
    List<Salary> findAllByStatus(SalaryStatus status);
    List<Salary> findAllByCollaboratorId(UUID id);
    List<Salary> findAllByEffectiveDateBetween(LocalDate initDate, LocalDate endDate);
    Page<Salary> findAll(Specification<Salary> specification, Pageable pageable);
    Page<Salary> findAllByCollaboratorId(UUID id, Pageable pageable);
    Page<Salary> findAll(Pageable pageable);
    Optional<Salary> findByCollaboratorAndStatus(Collaborator collaborator, SalaryStatus status);
    Optional<Salary> findById(UUID id);
    Salary findByStatusAndCollaboratorId(SalaryStatus status, UUID collaboratorId);
    boolean existsById(UUID id);
    void deleteById(UUID id);

}
