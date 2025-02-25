package com.humanit.portal_api.service;

import com.humanit.portal_api.dto.export.DatesDTO;
import com.humanit.portal_api.dto.export.EmailResponseDTO;
import com.humanit.portal_api.dto.export.SalaryStatusDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface ExportService {
    EmailResponseDTO exportAllSalariesBetweenDates(HttpServletRequest request, DatesDTO datesDTO);
    EmailResponseDTO exportAllSalaries(HttpServletRequest authHeader);
    EmailResponseDTO exportSalaryByCollaboratorId(HttpServletRequest request, UUID id);
    EmailResponseDTO exportAllSalariesByStatus(HttpServletRequest request, SalaryStatusDTO statusDTO);
    EmailResponseDTO exportAllOwnSalaries(HttpServletRequest request);
}
