package com.humanit.salary_api.service;

import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.exception.export.SalaryExportException;
import com.humanit.salary_api.model.Salary;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ExcelService {
    byte[] exportAllSalariesByStatus(SalaryStatus status) throws SalaryExportException;
    byte[] exportAllSalaries() throws SalaryExportException;
    byte[] exportSalaryByCollaboratorId(UUID id) throws SalaryExportException;
    byte[] exportAllSalariesBetweenDates(LocalDate initDate, LocalDate endDate) throws SalaryExportException;
    Workbook createWorkbookWithHeader();
    void fillSalaryData(Sheet sheet, List<Salary> salaries);
    void fillRowWithSalaryData(Row row, Salary salary);
    byte[] writeWorkbookToByteArray(Workbook workbook) throws SalaryExportException;
}
