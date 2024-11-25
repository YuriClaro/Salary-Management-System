package com.humanit.salary_api.validator;

import com.humanit.salary_api.exception.salary.SalaryByIdNotFoundException;
import com.humanit.salary_api.model.Salary;
import com.humanit.salary_api.repository.SalaryRepository;

import java.util.UUID;

public class SalaryValidator {
    public static Salary findSalaryById(UUID id, SalaryRepository repository) throws SalaryByIdNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new SalaryByIdNotFoundException(id));
    }
}
