package com.humanit.salary_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.humanit.salary_api.dto.SalaryDTO;
import com.humanit.salary_api.enumerator.Position;
import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.model.Collaborator;
import com.humanit.salary_api.model.Salary;
import com.humanit.salary_api.service.SalaryService;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {SalaryController.class})
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith({MockitoExtension.class})
public class SalaryControllerTest {
    private SalaryDTO salaryDTO;
    private Salary salary;
    private Collaborator collaborator;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalaryService salaryService;

    @Autowired
    private ObjectMapper objectMapper;

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
                .presentationDate(LocalDate.of(2025, 1, 1))
                .acceptanceDate(LocalDate.of(2025, 2, 1))
                .effectiveDate(LocalDate.of(2025, 3, 1))
                .build();

        salary = Salary.builder()
                .id(UUID.randomUUID())
                .collaborator(collaborator)
                .status(SalaryStatus.PROPOSED)
                .presentationDate(LocalDate.of(2025, 1, 1))
                .acceptanceDate(LocalDate.of(2025, 2, 1))
                .effectiveDate(LocalDate.of(2025, 3, 1))
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Test 1: Create Salary")
    public void CreateSalary_ReturnedCreated() throws Exception {
        given(salaryService.createSalary(ArgumentMatchers.any())).willReturn(salaryDTO);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/salary")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(salaryDTO)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(salaryDTO.getStatus().toString()))
                .andExpect(jsonPath("$.collaboratorId").value(salaryDTO.getCollaboratorId().toString()))
                .andExpect(jsonPath("$.presentationDate").value(salaryDTO.getPresentationDate().toString()))
                .andExpect(jsonPath("$.acceptanceDate").value(salaryDTO.getAcceptanceDate().toString()))
                .andExpect(jsonPath("$.effectiveDate").value(salaryDTO.getEffectiveDate().toString()));
    }


    @Test
    @Order(2)
    @DisplayName("Test 2: Get All Salaries")
    public void GetAllSalaries_ReturnSalariesDTO() throws Exception {
        Page<SalaryDTO> salaryDTOPage = new PageImpl(List.of(salaryDTO));
        given(salaryService.getAllSalaries(ArgumentMatchers.any(Pageable.class))).willReturn(salaryDTOPage);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/salary")
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("size", "10"));

        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].status")
                        .value(salaryDTO.getStatus().toString()))
                .andExpect(jsonPath("$.content[0].collaboratorId")
                        .value(salaryDTO.getCollaboratorId().toString()))
                .andExpect(jsonPath("$.content[0].presentationDate")
                        .value(salaryDTO.getPresentationDate().toString()))
                .andExpect(jsonPath("$.content[0].acceptanceDate")
                        .value(salaryDTO.getAcceptanceDate().toString()))
                .andExpect(jsonPath("$.content[0].effectiveDate")
                        .value(salaryDTO.getEffectiveDate().toString()));
    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Get Salary By Id")
    public void GetSalaryById_ReturnSalaryDTO() throws Exception {
        UUID salaryId = salary.getId();
        given(salaryService.getSalaryById(salaryId)).willReturn(salaryDTO);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/salary/{id}", salaryId)
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(salaryDTO.getStatus().toString()))
                .andExpect(jsonPath("$.collaboratorId").value(salaryDTO.getCollaboratorId().toString()))
                .andExpect(jsonPath("$.presentationDate").value(salaryDTO.getPresentationDate().toString()))
                .andExpect(jsonPath("$.acceptanceDate").value(salaryDTO.getAcceptanceDate().toString()))
                .andExpect(jsonPath("$.effectiveDate").value(salaryDTO.getEffectiveDate().toString()));
    }

    @Test
    @Order(4)
    @DisplayName("Test 4: Get All Salaries By Collaborator Id")
    public void GetAllSalariesByCollaboratorId_ReturnSalariesDTO() throws Exception {
        UUID collaboratorId = collaborator.getId();
        Page<SalaryDTO> salaryDTOPage = new PageImpl<>(List.of(salaryDTO));
        given(salaryService.getAllSalariesByCollaboratorId(
                ArgumentMatchers.any(UUID.class),
                ArgumentMatchers.any(Pageable.class))
        ).willReturn(salaryDTOPage);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/salary/collaboratorId={id}", collaboratorId)
                .contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("size", "10")
        );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].status")
                        .value(salaryDTO.getStatus().toString()))
                .andExpect(jsonPath("$.content[0].collaboratorId")
                        .value(salaryDTO.getCollaboratorId().toString()))
                .andExpect(jsonPath("$.content[0].presentationDate")
                        .value(salaryDTO.getPresentationDate().toString()))
                .andExpect(jsonPath("$.content[0].acceptanceDate")
                        .value(salaryDTO.getAcceptanceDate().toString()))
                .andExpect(jsonPath("$.content[0].effectiveDate")
                        .value(salaryDTO.getEffectiveDate().toString()));
    }

    @Test
    @Order(5)
    @DisplayName("Test 5: Update Salary")
    public void UpdateSalary_ReturnUpdatedSalaryDTO() throws Exception {
        UUID salaryId = salary.getId();
        given(salaryService.updateSalary(
                ArgumentMatchers.any(UUID.class),
                ArgumentMatchers.any(SalaryDTO.class))
        ).willReturn(salaryDTO);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/salary/{id}", salaryId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(salaryDTO))
        );

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(salaryDTO.getStatus().toString()))
                .andExpect(jsonPath("$.collaboratorId").value(salaryDTO.getCollaboratorId().toString()))
                .andExpect(jsonPath("$.presentationDate").value(salaryDTO.getPresentationDate().toString()))
                .andExpect(jsonPath("$.acceptanceDate").value(salaryDTO.getAcceptanceDate().toString()))
                .andExpect(jsonPath("$.effectiveDate").value(salaryDTO.getEffectiveDate().toString()));
    }

    @Test
    @Order(6)
    @DisplayName("Test 6: Delete Salary")
    public void DeleteSalary_ReturnNoContent() throws Exception {
        UUID salaryId = salary.getId();
        Mockito.doNothing().when(salaryService).deleteSalary(salaryId);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/salary/{id}", salaryId)
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(status().isNoContent());
    }

}
