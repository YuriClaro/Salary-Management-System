package com.humanit.salary_api.service;

import com.humanit.salary_api.dto.SalaryComponentDTO;
import com.humanit.salary_api.enumerator.ComponentType;
import com.humanit.salary_api.enumerator.Position;
import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.exception.salary.SalaryByIdNotFoundException;
import com.humanit.salary_api.exception.salaryComponent.SalaryComponentByIdNotFoundException;
import com.humanit.salary_api.mapper.SalaryComponentMapper;
import com.humanit.salary_api.model.Collaborator;
import com.humanit.salary_api.model.Salary;
import com.humanit.salary_api.model.SalaryComponent;
import com.humanit.salary_api.repository.SalaryComponentRepository;
import com.humanit.salary_api.repository.SalaryRepository;
import com.humanit.salary_api.service.impl.SalaryComponentServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@Rollback
@Transactional
@ExtendWith({MockitoExtension.class})
public class SalaryComponentServiceTest {
    private SalaryComponentDTO componentDTO;
    private SalaryComponent component;
    private Salary salary;
    
    @Mock
    private SalaryRepository salaryRepository;
    
    @Mock
    private SalaryComponentRepository componentRepository;
    
    @Mock
    private SalaryComponentMapper componentMapper;
    
    @InjectMocks
    private SalaryComponentServiceImpl componentService;

    @BeforeEach
    public void setUp() {
        Collaborator collaborator = Collaborator.builder()
                .id(UUID.randomUUID())
                .name("Test")
                .email("test@email.com")
                .position(Position.MANAGER)
                .build();

        salary = Salary.builder()
                .id(UUID.randomUUID())
                .collaborator(collaborator)
                .status(SalaryStatus.CURRENT)
                .presentationDate(LocalDate.of(2024, 1, 1))
                .acceptanceDate(LocalDate.of(2024, 2, 1))
                .effectiveDate(LocalDate.of(2024, 3, 1))
                .build();

        component = SalaryComponent.builder()
                .id(UUID.randomUUID())
                .salary(salary)
                .type(ComponentType.SALARY_BASE)
                .value(BigDecimal.valueOf(2000.0))
                .build();

        componentDTO = SalaryComponentDTO.builder()
                .salaryId(salary.getId())
                .type(ComponentType.SALARY_BASE)
                .value(BigDecimal.valueOf(2000.0))
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Test 1: Create Salary Component")
    public void createSalaryComponent_ReturnSalaryComponentDTO() throws SalaryByIdNotFoundException {
        when(salaryRepository.findById(componentDTO.getSalaryId())).thenReturn(Optional.of(salary));
        when(componentMapper.toEntity(componentDTO)).thenReturn(component);
        when(componentRepository.save(component)).thenReturn(component);
        when(componentMapper.toDTO(component)).thenReturn(componentDTO);

        SalaryComponentDTO returnedComponent = componentService.createSalaryComponent(componentDTO);

        assertThat(returnedComponent).isNotNull();
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Get all Salary Components")
    public void getAllSalariesComponents_ReturnsSalaryComponentDTO() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<SalaryComponent> componentPage = new PageImpl<>(List.of(component), pageable, 1L);
        SalaryComponentDTO expectedComponentDTO = componentDTO;

        when(componentRepository.findAll(pageable)).thenReturn(componentPage);
        when(componentMapper.toDTO(component)).thenReturn(expectedComponentDTO);

        Page<SalaryComponentDTO> result = componentService.getAllSalaryComponents(pageable);

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0)).isEqualTo(expectedComponentDTO);
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Get Salary Component by ID")
    public void getSalaryComponentById_ReturnsSalaryComponentDTO() throws SalaryComponentByIdNotFoundException {
        UUID componentId = component.getId();

        when(componentRepository.findById(componentId)).thenReturn(Optional.of(component));
        when(componentMapper.toDTO(component)).thenReturn(componentDTO);

        SalaryComponentDTO returnedComponent = componentService.getSalaryComponentById(componentId);

        assertThat(returnedComponent).isNotNull();
        assertThat(returnedComponent).isEqualTo(componentDTO);
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Update Salary Component")
    public void updateSalary_ReturnsUpdatedSalaryComponentDTO() throws SalaryComponentByIdNotFoundException {
        UUID componentId = component.getId();
        SalaryComponentDTO updatedComponentDTO = SalaryComponentDTO.builder()
                .salaryId(salary.getId())
                .type(ComponentType.SALARY_BASE)
                .value(BigDecimal.valueOf(3000.0))
                .build();

        when(componentRepository.findById(componentId)).thenReturn(Optional.of(component));
        doNothing().when(componentMapper).updateEntityFromDTO(updatedComponentDTO, component);
        when(componentRepository.save(component)).thenReturn(component);
        when(componentMapper.toDTO(component)).thenReturn(updatedComponentDTO);

        SalaryComponentDTO returnedComponent = componentService.updateSalaryComponent(componentId, updatedComponentDTO);

        assertThat(returnedComponent).isNotNull();
        assertThat(returnedComponent.getValue()).isEqualTo(BigDecimal.valueOf(3000.0));
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Get All Salary Components By Salary ID")
    public void getAllSalariesBySalaryId_ReturnsSalaryDTO() throws SalaryByIdNotFoundException {
        UUID salaryId = salary.getId();
        Pageable pageable = PageRequest.of(0, 10);
        Page<SalaryComponent> componentPage = new PageImpl<>(Collections.singletonList(component));

        when(salaryRepository.findById(salaryId)).thenReturn(Optional.of(salary));
        when(componentRepository.findAllBySalaryId(salaryId, pageable)).thenReturn(componentPage);
        when(componentMapper.toDTO(component)).thenReturn(componentDTO);

        Page<SalaryComponentDTO> returnedComponent = componentService.getAllSalaryComponentBySalaryId(salaryId, pageable);

        assertThat(returnedComponent).isNotNull();
        assertThat(returnedComponent.getTotalElements()).isEqualTo(1L);
        assertThat(returnedComponent.getTotalPages()).isEqualTo(1);
        assertThat(returnedComponent.getContent()).hasSize(1);
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Delete Salary Component")
    public void deleteSalaryComponent_DeletesSalaryComponentSuccessfully() {
        UUID componentId = component.getId();

        when(componentRepository.findById(componentId)).thenReturn(Optional.of(component));

        assertAll(() -> componentService.deleteSalaryComponent(componentId));
    }

}
