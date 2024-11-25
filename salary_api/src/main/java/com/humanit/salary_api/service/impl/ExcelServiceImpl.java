package com.humanit.salary_api.service.impl;

import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.exception.export.SalaryExportException;
import com.humanit.salary_api.model.Salary;
import com.humanit.salary_api.model.SalaryComponent;
import com.humanit.salary_api.repository.SalaryRepository;
import com.humanit.salary_api.service.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.humanit.salary_api.enumerator.CoverFlex.*;
import static com.humanit.salary_api.enumerator.CoverFlex.GYM_BENEFIT;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {
    private final SalaryRepository salaryRepository;

    @Override
    public byte[] exportAllSalariesByStatus(SalaryStatus status) throws SalaryExportException {
        log.info("Exporting all salaries by status.");
        List<Salary> salaries = salaryRepository.findAllByStatus(status);
        Workbook workbook = createWorkbookWithHeader();
        Sheet sheet = workbook.getSheetAt(0);
        fillSalaryData(sheet, salaries);
        log.info("Excel file generated successfully with {} salaries", salaries.size());
        return writeWorkbookToByteArray(workbook);
    }


    @Override
    public byte[] exportAllSalariesBetweenDates(LocalDate initDate, LocalDate endDate) throws SalaryExportException {
        log.info("Exporting all salaries between dates to Excel");
        List<Salary> salaries = salaryRepository.findAllByEffectiveDateBetween(initDate, endDate);
        Workbook workbook = createWorkbookWithHeader();
        Sheet sheet = workbook.getSheetAt(0);
        fillSalaryData(sheet, salaries);
        log.info("Excel file generated successfully with {} salaries", salaries.size());
        return writeWorkbookToByteArray(workbook);
    }

    @Override
    public byte[] exportAllSalaries() throws SalaryExportException {
        log.info("Exporting all salaries to Excel.");
        List<Salary> salaries = salaryRepository.findAll();
        Workbook workbook = createWorkbookWithHeader();
        Sheet sheet = workbook.getSheetAt(0);
        fillSalaryData(sheet, salaries);
        log.info("Excel file generated successfully with {} salaries.", salaries.size());
        return writeWorkbookToByteArray(workbook);
    }

    @Override
    public byte[] exportSalaryByCollaboratorId(UUID id) throws SalaryExportException {
        log.info("Exporting salaries for collaborator ID: {}", id);
        List<Salary> salaries = salaryRepository.findAllByCollaboratorId(id);
        Workbook workbook = createWorkbookWithHeader();
        Sheet sheet = workbook.getSheetAt(0);
        fillSalaryData(sheet, salaries);
        log.info("Excel file generated successfully for collaborator ID: {}", id);
        return writeWorkbookToByteArray(workbook);
    }

    @Override
    public Workbook createWorkbookWithHeader() {
        log.info("Creating Excel workbook with header.");
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Salaries");

        Row headerRow = sheet.createRow(0);
        String[] headers = {"SalarySpecification ID", "Collaborator ID", "Status", "Presentation Date", "Acceptance Date",
                "Effective Date", "SalarySpecification Base", "Exemption", "Subsistence Allowance",
                "Transport Voucher", "Meal Voucher", "Health Insurance", "Gym benefit"};

        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
        log.info("Excel header created successfully.");
        return workbook;
    }

    @Override
    public void fillSalaryData(Sheet sheet, List<Salary> salaries) {
        log.info("Filling sheet with salary data. Total rows to add: {}", salaries.size());
        int rowNum = 1;
        for (Salary salary : salaries) {
            Row row = sheet.createRow(rowNum++);
            fillRowWithSalaryData(row, salary);
        }
        log.info("Sheet filled with salary data successfully.");
    }

    @Override
    public void fillRowWithSalaryData(Row row, Salary salary) {
        log.debug("Filling row for salary ID: {}", salary.getId());
        BigDecimal salaryBase = BigDecimal.ZERO;
        BigDecimal exemption = BigDecimal.ZERO;
        BigDecimal subsistenceAllowance = BigDecimal.ZERO;
        BigDecimal transportVoucher = BigDecimal.ZERO;
        BigDecimal gymBenefit = BigDecimal.ZERO;
        BigDecimal healthInsurance = BigDecimal.ZERO;
        BigDecimal mealVoucher = BigDecimal.ZERO;

        for (SalaryComponent component : salary.getSalaryComponentList()) {
            switch (component.getType()) {
                case SALARY_BASE -> salaryBase = component.getValue();
                case EXEMPTION -> exemption = component.getValue();
                case SUBSISTENCE_ALLOWANCE -> subsistenceAllowance = component.getValue();
                case COVERFLEX -> {
                    if (HEALTH_INSURANCE.equals(component.getCoverFlex())) healthInsurance = component.getValue();
                    if (MEAL_VOUCHER.equals(component.getCoverFlex())) mealVoucher = component.getValue();
                    if (TRANSPORT_VOUCHER.equals(component.getCoverFlex())) transportVoucher = component.getValue();
                    if (GYM_BENEFIT.equals(component.getCoverFlex())) gymBenefit = component.getValue();
                }
            }
        }

        row.createCell(0).setCellValue(salary.getId().toString());
        row.createCell(1).setCellValue(salary.getCollaborator().getId().toString());
        row.createCell(2).setCellValue(salary.getStatus().toString());
        row.createCell(3).setCellValue(salary.getPresentationDate().toString());
        row.createCell(4).setCellValue(salary.getAcceptanceDate().toString());
        row.createCell(5).setCellValue(salary.getEffectiveDate().toString());
        row.createCell(6).setCellValue(String.valueOf(salaryBase));
        row.createCell(7).setCellValue(String.valueOf(exemption));
        row.createCell(8).setCellValue(String.valueOf(subsistenceAllowance));
        row.createCell(9).setCellValue(String.valueOf(healthInsurance));
        row.createCell(10).setCellValue(String.valueOf(mealVoucher));
        row.createCell(11).setCellValue(String.valueOf(transportVoucher));
        row.createCell(12).setCellValue(String.valueOf(gymBenefit));
        log.debug("Row filled for salary ID: {}", salary.getId());
    }

    @Override
    public byte[] writeWorkbookToByteArray(Workbook workbook) throws SalaryExportException {
        log.info("Writing workbook to byte array.");
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            log.error("Error while writing workbook to byte array.", e);
            throw new SalaryExportException();
        }
    }
}
