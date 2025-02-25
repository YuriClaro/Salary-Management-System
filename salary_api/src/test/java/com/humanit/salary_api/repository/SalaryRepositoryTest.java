package com.humanit.salary_api.repository;

import com.humanit.salary_api.enumerator.Position;
import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.model.Collaborator;
import com.humanit.salary_api.model.Salary;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Rollback
@DataJpaTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SalaryRepositoryTest {
    private Salary salary;

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @BeforeEach
    public void setUp() {
        Collaborator collaborator = Collaborator.builder()
                .name("Test")
                .email("test@email.com")
                .position(Position.MANAGER)
                .build();

        salary = Salary.builder()
                .collaborator(collaborator)
                .status(SalaryStatus.PROPOSED)
                .presentationDate(LocalDate.of(2024,01,01))
                .acceptanceDate(LocalDate.of(2024,02,01))
                .effectiveDate(LocalDate.of(2024,03,01))
                .build();

        collaboratorRepository.save(collaborator);
    }

    @Test
    @Order(1)
    @DisplayName("Test 1: Save Salary Test")
    public void SaveAll_ReturnSavedSalary() {
        Salary savedSalary = salaryRepository.save(salary);

        Assertions.assertThat(savedSalary).isNotNull();
        Assertions.assertThat(savedSalary.getId()).isEqualTo(salary.getId());
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Find Salary By ID")
    public void FindById_ReturnSavedSalary() {
        salaryRepository.save(salary);

        Salary returnedSalary = salaryRepository.findById(salary.getId()).get();

        Assertions.assertThat(returnedSalary).isNotNull();
        Assertions.assertThat(returnedSalary.getId()).isEqualTo(salary.getId());
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Update Salary")
    public void UpdateSalary_ReturnSalaryNotNull() {
        salaryRepository.save(salary);

        Salary savedSalary = salaryRepository.findById(salary.getId()).get();
        savedSalary.setStatus(SalaryStatus.ARCHIVED);

        Salary updatedSalary = salaryRepository.save(savedSalary);

        Assertions.assertThat(updatedSalary.getStatus().equals(SalaryStatus.ARCHIVED));
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Delete Salary")
    public void DeleteSalary_ReturnSalaryIsEmpty() {
        salaryRepository.save(salary);

        salaryRepository.deleteById(salary.getId());
        Optional<Salary> salaryReturn = salaryRepository.findById(salary.getId());

        Assertions.assertThat(salaryReturn).isEmpty();
    }
}
