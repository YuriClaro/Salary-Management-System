package com.humanit.salary_api.exception.salary;

import lombok.Getter;
import org.springframework.http.HttpStatus;


import static com.humanit.salary_api.exception.ErrorEnum.SALARY_INVALID_DECISION;

@Getter
public class InvalidSalaryDecisionException extends Exception{
    private final String message;
    private final String code;
    private final HttpStatus status;

    public InvalidSalaryDecisionException() {
        this.message = SALARY_INVALID_DECISION.getMessage();
        this.code = SALARY_INVALID_DECISION.getCode();
        this.status = SALARY_INVALID_DECISION.getStatus();
    }
}
