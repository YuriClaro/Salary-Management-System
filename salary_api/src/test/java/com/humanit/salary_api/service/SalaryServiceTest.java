package com.humanit.salary_api.service;

import com.humanit.salary_api.dto.SalaryDTO;
import com.humanit.salary_api.enumerator.Position;
import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.exception.collaborator.CollaboratorByIdNotFoundException;
import com.humanit.salary_api.exception.salary.SalaryByIdNotFoundException;
import com.humanit.salary_api.mapper.SalaryMapper;
import com.humanit.salary_api.model.Collaborator;
import com.humanit.salary_api.model.Salary;
import com.humanit.salary_api.repository.CollaboratorRepository;
import com.humanit.salary_api.repository.SalaryRepository;
import com.humanit.salary_api.service.impl.SalaryServiceImpl;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.humanit.salary_api.validator.CollaboratorValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@Rollback
@Transactional
@ExtendWith({MockitoExtension.class})
public class SalaryServiceTest {
    private SalaryDTO salaryDTO;
    private Collaborator collaborator;
    private Salary salary;

    @Mock
    private SalaryRepository salaryRepository;

    @Mock
    private CollaboratorRepository collaboratorRepository;

    @Mock
    private SalaryMapper salaryMapper;

    @Mock
    private CollaboratorValidator collaboratorValidator;

    @InjectMocks
    private SalaryServiceImpl salaryService;

    @BeforeEach
    public void setUp() {
        collaborator = Collaborator.builder()
                .id(UUID.randomUUID())
                .name("Test")
                .email("test@email.com")
                .position(Position.MANAGER)
                .build();
        salaryDTO = SalaryDTO.builder()
                .collaboratorId(collaborator.getId())
                .status(SalaryStatus.PROPOSED)
                .presentationDate(LocalDate.of(2024, 1, 1))
                .acceptanceDate(LocalDate.of(2024, 2, 1))
                .effectiveDate(LocalDate.of(2024, 3, 1))
                .build();
        salary = Salary.builder()
                .id(UUID.randomUUID())
                .collaborator(collaborator)
                .status(SalaryStatus.PROPOSED)
                .presentationDate(LocalDate.of(2024, 1, 1))
                .acceptanceDate(LocalDate.of(2024, 2, 1))
                .effectiveDate(LocalDate.of(2024, 3, 1))
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Test 1: Create Salary")
    public void CreateSalary_ReturnSalaryDTO() throws CollaboratorByIdNotFoundException {
        when(collaboratorRepository.findById(salaryDTO.getCollaboratorId())).thenReturn(Optional.of(collaborator));
        when(salaryMapper.toEntity(salaryDTO)).thenReturn(salary);
        when(salaryRepository.save(salary)).thenReturn(salary);
        when(salaryMapper.toDTO(salary)).thenReturn(salaryDTO);

        SalaryDTO returnedSalary = salaryService.createSalary(salaryDTO);

        assertThat(returnedSalary).isNotNull();
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Get All Salaries")
    public void GetAllSalaries_ReturnsResponseDTO() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Salary> salaryPage = new PageImpl<>(List.of(salary), pageable, 1L);
        SalaryDTO expectedSalaryDTO = salaryDTO;

        when(salaryRepository.findAll(pageable)).thenReturn(salaryPage);
        when(salaryMapper.toDTO(salary)).thenReturn(expectedSalaryDTO);

        Page<SalaryDTO> result = salaryService.getAllSalaries(pageable);

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0)).isEqualTo(expectedSalaryDTO);
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Get Salary by ID")
    public void GetSalaryById_ReturnsSalaryDTO() throws SalaryByIdNotFoundException {
        UUID salaryId = salary.getId();

        when(salaryRepository.findById(salaryId)).thenReturn(Optional.of(salary));
        when(salaryMapper.toDTO(salary)).thenReturn(salaryDTO);

        SalaryDTO returnedSalary = salaryService.getSalaryById(salaryId);

        assertThat(returnedSalary).isNotNull();
        assertThat(returnedSalary).isEqualTo(salaryDTO);
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Update Salary")
    public void UpdateSalary_ReturnsUpdatedSalaryDTO() throws SalaryByIdNotFoundException {
        UUID salaryId = salary.getId();
        SalaryDTO updatedSalaryDTO = SalaryDTO.builder()
                .collaboratorId(collaborator.getId())
                .status(SalaryStatus.PENDING)
                .presentationDate(salaryDTO.getPresentationDate())
                .acceptanceDate(salaryDTO.getAcceptanceDate())
                .effectiveDate(salaryDTO.getEffectiveDate())
                .build();

        when(salaryRepository.findById(salaryId)).thenReturn(Optional.of(salary));
        doNothing().when(salaryMapper).updateEntityFromDTO(updatedSalaryDTO, salary);
        when(salaryRepository.save(salary)).thenReturn(salary);
        when(salaryMapper.toDTO(salary)).thenReturn(updatedSalaryDTO);

        SalaryDTO result = salaryService.updateSalary(salaryId, updatedSalaryDTO);

        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo(SalaryStatus.PENDING);
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Get All Salaries By Collaborator ID")
    public void GetAllSalariesByCollaboratorId_ReturnsSalaryDTO() throws CollaboratorByIdNotFoundException {
        UUID collaboratorId = collaborator.getId();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Salary> salaryPage = new PageImpl<>(Collections.singletonList(salary));

        when(collaboratorRepository.findById(collaboratorId)).thenReturn(Optional.of(collaborator));
        when(salaryRepository.findAllByCollaboratorId(collaboratorId, pageable)).thenReturn(salaryPage);
        when(salaryMapper.toDTO(salary)).thenReturn(salaryDTO);

        Page<SalaryDTO> result = salaryService.getAllSalariesByCollaboratorId(collaboratorId, pageable);

        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(1L);
        assertThat(result.getTotalPages()).isEqualTo(1);
        assertThat(result.getContent()).hasSize(1);
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Delete Salary")
    public void DeleteSalary_DeletesSalarySuccessfully() throws SalaryByIdNotFoundException {
        UUID salaryId = salary.getId();

        when(salaryRepository.findById(salaryId)).thenReturn(Optional.of(salary));

        assertAll(() -> salaryService.deleteSalary(salaryId));
    }
}