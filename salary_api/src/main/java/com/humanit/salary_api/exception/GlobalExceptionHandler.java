package com.humanit.salary_api.exception;

import com.humanit.salary_api.exception.collaborator.CollaboratorByIdNotFoundException;
import com.humanit.salary_api.exception.export.SalaryExportException;
import com.humanit.salary_api.exception.general.GenericException;
import com.humanit.salary_api.exception.optimisticLocking.OptimisticLockingException;
import com.humanit.salary_api.exception.salary.InvalidSalaryDecisionException;
import com.humanit.salary_api.exception.salary.SalaryByIdNotFoundException;
import com.humanit.salary_api.exception.salaryComponent.SalaryComponentByIdNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SalaryComponentByIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSalaryComponentByIdNotFoundException(SalaryComponentByIdNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(SalaryByIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSalaryByIdNotFoundException(SalaryByIdNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(CollaboratorByIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCollaboratorByIdNotFoundException(CollaboratorByIdNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(OptimisticLockingException.class)
    public ResponseEntity<ErrorResponse> handleOptimisticLockException(OptimisticLockingException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(SalaryExportException.class)
    public ResponseEntity<ErrorResponse> handleSalaryExportException(SalaryExportException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(GenericException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(InvalidSalaryDecisionException.class)
    public ResponseEntity<ErrorResponse> handleInvalidSalaryDecisionException(InvalidSalaryDecisionException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }
}
