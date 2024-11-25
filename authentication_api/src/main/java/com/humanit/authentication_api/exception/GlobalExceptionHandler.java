package com.humanit.authentication_api.exception;

import com.humanit.authentication_api.exception.generic.GenericException;
import com.humanit.authentication_api.exception.optimisticLocking.OptimisticLockingException;
import com.humanit.authentication_api.exception.refreshToken.RefreshTokenExpiredException;
import com.humanit.authentication_api.exception.refreshToken.RefreshTokenNotFoundException;
import com.humanit.authentication_api.exception.userCredentials.UserCredentialsNotAuthenticatedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserCredentialsNotAuthenticatedException.class)
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

    @ExceptionHandler(OptimisticLockingException.class)
    public ResponseEntity<ErrorResponse> handleOptimisticLockException(OptimisticLockingException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(GenericException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getStatus().value());
        return ResponseEntity.status(e.getStatus()).body(errorResponse);
    }
}
