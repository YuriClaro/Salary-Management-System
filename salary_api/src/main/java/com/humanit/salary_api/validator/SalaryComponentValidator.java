package com.humanit.salary_api.validator;

import com.humanit.salary_api.exception.salaryComponent.SalaryComponentByIdNotFoundException;
import com.humanit.salary_api.model.SalaryComponent;
import com.humanit.salary_api.repository.SalaryComponentRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SalaryComponentValidator {
    public static SalaryComponent findSalaryComponentById(
            UUID id, SalaryComponentRepository repository) throws SalaryComponentByIdNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new SalaryComponentByIdNotFoundException(id));
    }
}
