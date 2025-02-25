package com.humanit.salary_api.repository;

import com.humanit.salary_api.model.SalaryComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SalaryComponentRepository extends JpaRepository<SalaryComponent, UUID> {
    Page<SalaryComponent> findAll(Pageable pageable);
    Page<SalaryComponent> findAllBySalaryId(UUID id, Pageable pageable);
    Optional<SalaryComponent> findById(UUID id);
    boolean existsById(UUID id);
    void deleteById(UUID id);
}
