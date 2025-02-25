package com.humanit.salary_api.service;

import com.humanit.salary_api.enumerator.Position;
import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.model.Collaborator;
import com.humanit.salary_api.model.Salary;
import com.humanit.salary_api.repository.SalaryRepository;
import com.humanit.salary_api.service.impl.ExcelServiceImpl;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ExcelServiceTest {
    private Collaborator collaborator;
    private Salary salary;

    @Mock
    private SalaryRepository salaryRepository;

    @InjectMocks
    private ExcelServiceImpl excelService;

    private List<Salary> mockSalaries;

    @BeforeEach
    void setUp() {
        collaborator = Collaborator.builder()
                .id(UUID.randomUUID())
                .name("Test")
                .email("test@email.com")
                .position(Position.MANAGER)
                .build();
        salary = Salary.builder()
                .id(UUID.randomUUID())
                .collaborator(collaborator)
                .status(SalaryStatus.PROPOSED)
                .presentationDate(LocalDate.of(2024, 1, 1))
                .acceptanceDate(LocalDate.of(2024, 2, 1))
                .effectiveDate(LocalDate.of(2024, 3, 1))
                .salaryComponentList(new ArrayList<>())
                .build();

        mockSalaries = List.of(salary);
    }


    @Test
    @Order(1)
    @DisplayName("Generate an Excel file with salaries by status 'CURRENT'")
    void testExportAllSalariesByStatus() throws Exception {
        Mockito.when(salaryRepository.findAllByStatus(SalaryStatus.CURRENT)).thenReturn(mockSalaries);

        byte[] excelBytes = excelService.exportAllSalariesByStatus(SalaryStatus.CURRENT);

        assertNotNull(excelBytes, "Excel bytes should not be null");
        assertTrue(excelBytes.length > 0, "Excel file should not be empty");

        try (Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(excelBytes))) {
            Sheet sheet = workbook.getSheetAt(0);
            assertEquals("Salaries", sheet.getSheetName(), "Sheet name should be 'Salaries'");
            Row headerRow = sheet.getRow(0);
            assertEquals("SalarySpecification ID", headerRow.getCell(0).getStringCellValue(),
                    "First header should be 'SalarySpecification ID'");
        }
    }

    @Test
    @Order(2)
    @DisplayName("Generate an Excel file with all salaries between specified dates")
    void testExportAllSalariesBetweenDates() throws Exception {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);

        Mockito.when(salaryRepository.findAllByEffectiveDateBetween(startDate, endDate)).thenReturn(mockSalaries);

        byte[] excelBytes = excelService.exportAllSalariesBetweenDates(startDate, endDate);

        assertNotNull(excelBytes);
        assertTrue(excelBytes.length > 0);
    }

    @Test
    @Order(3)
    @DisplayName("Generate an Excel file with salary details for a specific collaborator")
    void testExportSalaryByCollaboratorId() throws Exception {
        UUID collaboratorId = mockSalaries.get(0).getCollaborator().getId();

        Mockito.when(salaryRepository.findAllByCollaboratorId(collaboratorId)).thenReturn(mockSalaries);

        byte[] excelBytes = excelService.exportSalaryByCollaboratorId(collaboratorId);

        assertNotNull(excelBytes);
        assertTrue(excelBytes.length > 0);
    }
}
