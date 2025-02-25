package com.humanit.salary_api.exception.export;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.humanit.salary_api.exception.ErrorEnum.SALARY_EXPORT_EXCEPTION;

@Getter
public class SalaryExportException extends Exception {
    private final String message;
    private final String code;
    private final HttpStatus status;

    public SalaryExportException() {
        super(SALARY_EXPORT_EXCEPTION.getMessage());
        this.message = SALARY_EXPORT_EXCEPTION.getMessage();
        this.code = SALARY_EXPORT_EXCEPTION.getCode();
        this.status = SALARY_EXPORT_EXCEPTION.getStatus();
    }
}
