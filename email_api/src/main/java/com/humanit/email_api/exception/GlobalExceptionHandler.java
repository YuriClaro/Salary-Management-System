package com.humanit.email_api.exception;

import com.humanit.email_api.exception.email.EmailProcessingException;
import com.humanit.email_api.exception.email.EmailSendException;
import com.humanit.email_api.exception.general.GenericException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailProcessingException.class)
    public ResponseEntity<ErrorResponse> handleEmailProcessingException(EmailProcessingException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(EmailSendException.class)
    public ResponseEntity<ErrorResponse> handleEmailSendException(EmailSendException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(GenericException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }
}
