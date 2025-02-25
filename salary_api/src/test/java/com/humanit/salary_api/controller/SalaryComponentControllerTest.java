package com.humanit.salary_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.humanit.salary_api.dto.SalaryComponentDTO;
import com.humanit.salary_api.enumerator.ComponentType;
import com.humanit.salary_api.enumerator.Position;
import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.model.Collaborator;
import com.humanit.salary_api.model.Salary;
import com.humanit.salary_api.model.SalaryComponent;
import com.humanit.salary_api.service.SalaryComponentService;
import com.humanit.salary_api.service.SalaryService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {SalaryComponentController.class})
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith({MockitoExtension.class})
public class SalaryComponentControllerTest {
    private SalaryComponentDTO componentDTO;
    private SalaryComponent component;
    private Salary salary;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalaryComponentService componentService;

    @MockBean
    private SalaryService salaryService;

    @Autowired
    private ObjectMapper objectMapper;

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
    public void CreateSalaryComponent_ReturnedCreated() throws Exception {
        given(componentService.createSalaryComponent(ArgumentMatchers.any())).willReturn(componentDTO);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/salary/components")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(componentDTO)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.salaryId")
                        .value(componentDTO.getSalaryId().toString()))
                .andExpect(jsonPath("$.type")
                        .value(componentDTO.getType().toString()))
                .andExpect(jsonPath("$.value")
                        .value(componentDTO.getValue().toString()));
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Get All Salaries Components")
    public void GetAllSalariesComponents_ReturnSalariesComponentsDTO() throws Exception {
        Page<SalaryComponentDTO> componentDTOPage = new PageImpl<>(List.of(componentDTO));
        given(componentService.getAllSalaryComponents(ArgumentMatchers.any(Pageable.class))).willReturn(componentDTOPage);

        ResultActions response = mockMvc.perform(get("/api/v1/salary/components")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("size", "10"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].salaryId")
                        .value(componentDTO.getSalaryId().toString()))
                .andExpect(jsonPath("$.content[0].type")
                        .value(componentDTO.getType().toString()))
                .andExpect(jsonPath("$.content[0].value")
                        .value(componentDTO.getValue().toString()));
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Get Salary Components By Id")
    public void getSalaryComponentsById_ReturnSalaryDTO() throws Exception {
        UUID componentId = component.getId();
        given(componentService.getSalaryComponentById(componentId)).willReturn(componentDTO);

        ResultActions response = mockMvc
                .perform(get("/api/v1/salary/components/{id}", componentId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.salaryId")
                        .value(componentDTO.getSalaryId().toString()))
                .andExpect(jsonPath("$.type")
                        .value(componentDTO.getType().toString()))
                .andExpect(jsonPath("$.value")
                        .value(componentDTO.getValue()));
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Get All Salaries Components By Salary Id")
    public void GetAllSalariesComponentsBySalaryId_ReturnSalariesDTO() throws Exception {
        UUID salaryId = salary.getId();
        Page<SalaryComponentDTO> componentDTOPage = new PageImpl<>(List.of(componentDTO));
        given(componentService.getAllSalaryComponentBySalaryId(
                ArgumentMatchers.any(UUID.class), ArgumentMatchers.any(Pageable.class))).willReturn(componentDTOPage);

        ResultActions response = mockMvc
                .perform(get("/api/v1/salary/components/salaryId={id}", salaryId)
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("size", "10"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].salaryId").value(componentDTO.getSalaryId().toString()))
                .andExpect(jsonPath("$.content[0].type").value(componentDTO.getType().toString()))
                .andExpect(jsonPath("$.content[0].value").value(componentDTO.getValue().toString()));
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Update Salary Component")
    public void UpdateSalaryComponent_ReturnUpdatedSalaryDTO() throws Exception {
        UUID componentId = component.getId();
        given(componentService.updateSalaryComponent(
                ArgumentMatchers.any(UUID.class), ArgumentMatchers.any(SalaryComponentDTO.class))).willReturn(componentDTO);

        ResultActions response = mockMvc.perform(put("/api/v1/salary/components/{id}", componentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(componentDTO)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.salaryId").value(componentDTO.getSalaryId().toString()))
                .andExpect(jsonPath("$.type").value(componentDTO.getType().toString()))
                .andExpect(jsonPath("$.value").value(componentDTO.getValue()));
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Delete Salary Components")
    public void DeleteSalaryComponents_ReturnNoContent() throws Exception {
        UUID componentsId = component.getId();
        doNothing().when(componentService).deleteSalaryComponent(componentsId);

        ResultActions response = mockMvc.perform(delete("/api/v1/salary/components/{id}", componentsId)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }
}

