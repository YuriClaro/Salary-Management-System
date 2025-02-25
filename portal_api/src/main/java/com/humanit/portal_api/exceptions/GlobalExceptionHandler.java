package com.humanit.portal_api.exceptions;

import com.humanit.portal_api.exceptions.authenticationApi.RefreshTokenExpiredException;
import com.humanit.portal_api.exceptions.authenticationApi.RefreshTokenNotFoundException;
import com.humanit.portal_api.exceptions.authenticationApi.UserCredentialsNotAuthenticatedException;
import com.humanit.portal_api.exceptions.generic.GenericException;
import com.humanit.portal_api.exceptions.jwt.InvalidAuthorizationHeaderException;
import com.humanit.portal_api.exceptions.salaryApi.CollaboratorByIdNotFoundException;
import com.humanit.portal_api.exceptions.salaryApi.InvalidSalaryDecisionException;
import com.humanit.portal_api.exceptions.salaryApi.SalaryByIdNotFoundException;
import com.humanit.portal_api.exceptions.salaryApi.SalaryComponentByIdNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(GenericException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

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

    public ResponseEntity<ErrorResponse> handleUserCredentialsNotAuthenticatedException(
            UserCredentialsNotAuthenticatedException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<ErrorResponse> handleRefreshTokenExpiredException(RefreshTokenExpiredException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRefreshTokenNotFoundException(RefreshTokenNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(InvalidAuthorizationHeaderException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAuthorizationHeaderException(InvalidAuthorizationHeaderException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(InvalidSalaryDecisionException.class)
    public ResponseEntity<ErrorResponse> handleInvalidSalaryDecisionException(InvalidSalaryDecisionException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }
}
