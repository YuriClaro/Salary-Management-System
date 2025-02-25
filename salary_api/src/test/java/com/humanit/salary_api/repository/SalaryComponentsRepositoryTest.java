package com.humanit.salary_api.repository;

import com.humanit.salary_api.enumerator.ComponentType;
import com.humanit.salary_api.enumerator.Position;
import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.model.Collaborator;
import com.humanit.salary_api.model.Salary;
import com.humanit.salary_api.model.SalaryComponent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Rollback
@DataJpaTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SalaryComponentsRepositoryTest {
    private SalaryComponent component;

    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private SalaryComponentRepository componentRepository;

    @BeforeEach
    public void setUp() {
        Collaborator collaborator = Collaborator.builder()
                .name("Test")
                .email("test@email.com")
                .position(Position.MANAGER)
                .build();

        Salary salary = Salary.builder()
                .collaborator(collaborator)
                .status(SalaryStatus.PROPOSED)
                .presentationDate(LocalDate.of(2024,01,01))
                .acceptanceDate(LocalDate.of(2024,02,01))
                .effectiveDate(LocalDate.of(2024,03,01))
                .build();

        component = SalaryComponent.builder()
                .salary(salary)
                .type(ComponentType.SALARY_BASE)
                .value(BigDecimal.valueOf(1000.00))
                .build();

        collaboratorRepository.save(collaborator);
        salaryRepository.save(salary);
    }

    @Test
    @Order(1)
    @DisplayName("Test 1: Save Salary Component")
    public void SaveAll_ReturnSavedSalaryComponents() {
        SalaryComponent savedSalary = componentRepository.save(component);

        Assertions.assertThat(savedSalary).isNotNull();
        Assertions.assertThat(savedSalary.getId()).isEqualTo(component.getId());
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Find Salary Component By ID")
    public void FindById_ReturnSavedSalaryComponents() {
        componentRepository.save(component);

        SalaryComponent returnedSalary = componentRepository.findById(component.getId()).get();

        Assertions.assertThat(returnedSalary).isNotNull();
        Assertions.assertThat(returnedSalary.getId()).isEqualTo(component.getId());
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Update Salary Component")
    public void UpdateSalaryComponents_ReturnSalaryComponentsNotNull() {
        componentRepository.save(component);

        SalaryComponent savedSalary = componentRepository.findById(component.getId()).get();
        savedSalary.setValue(BigDecimal.valueOf(2000.00));

        SalaryComponent updatedSalary = componentRepository.save(savedSalary);

        Assertions.assertThat(updatedSalary.getValue().equals(BigDecimal.valueOf(2000.00)));
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Delete Salary Component")
    public void DeleteSalaryComponents_ReturnSalaryComponentsIsEmpty() {
        componentRepository.save(component);

        salaryRepository.deleteById(component.getId());
        Optional<Salary> salaryReturn = salaryRepository.findById(component.getId());

        Assertions.assertThat(salaryReturn).isEmpty();
    }
}
