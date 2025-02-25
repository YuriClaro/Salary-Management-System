package com.humanit.salary_api.service;

import com.humanit.salary_api.dto.DatesRequestDTO;
import com.humanit.salary_api.dto.EmailDTO;
import com.humanit.salary_api.dto.EmailWithCollaboratorIdDTO;
import com.humanit.salary_api.dto.EmailWithStatusDTO;
import com.humanit.salary_api.exception.collaborator.CollaboratorByIdNotFoundException;
import com.humanit.salary_api.exception.collaborator.InvalidCollaboratorException;
import com.humanit.salary_api.exception.export.SalaryExportException;

public interface ExportService {
    void handleExportAllSalariesByStatus(EmailWithStatusDTO emailWithStatusDTO) throws SalaryExportException;
    void handleExportAllSalariesBetweenDates(DatesRequestDTO datesRequestDTO) throws SalaryExportException;
    void handleExportAllSalaries(EmailDTO emailDTO) throws SalaryExportException;
    void handleExportSalaryByCollaboratorId(EmailWithCollaboratorIdDTO emailDTO) throws SalaryExportException;
    void handleExportAllOwnSalaries(EmailDTO emailDTO)
            throws SalaryExportException, InvalidCollaboratorException, CollaboratorByIdNotFoundException;
}
