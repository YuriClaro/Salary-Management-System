package com.humanit.authentication_api.exception.optimisticLocking;

import jakarta.persistence.OptimisticLockException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.humanit.authentication_api.exception.ErrorEnum.OPTIMISTIC_LOCKING_EXCEPTION;

@Getter
public class OptimisticLockingException extends OptimisticLockException {
    private final String message;
    private final String code;
    private final HttpStatus status;

    public OptimisticLockingException(OptimisticLockException e) {
        super(OPTIMISTIC_LOCKING_EXCEPTION.getMessage(e));
        this.message = OPTIMISTIC_LOCKING_EXCEPTION.getMessage(e);
        this.code = OPTIMISTIC_LOCKING_EXCEPTION.getCode();
        this.status = OPTIMISTIC_LOCKING_EXCEPTION.getStatus();
    }
}


