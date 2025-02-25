package com.humanit.salary_api.scheduler;

import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.model.Collaborator;
import com.humanit.salary_api.model.Salary;
import com.humanit.salary_api.repository.CollaboratorRepository;
import com.humanit.salary_api.repository.SalaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SalaryScheduler {
    private final SalaryRepository salaryRepository;
    private final CollaboratorRepository collaboratorRepository;

    @Scheduled(cron = "0 0 0 * * ?", zone = "America/Sao_Paulo")
    @Transactional
    public void updateSalariesByEffectiveDate() {
        LocalDate today = LocalDate.now();
        List<Collaborator> collaborators = collaboratorRepository.findAll();

        for (Collaborator collaborator : collaborators) {
            List<Salary> salaries = collaborator.getSalaries();

            salaries.stream()
                    .filter(salary -> salary.getEffectiveDate().isEqual(today)
                            && salary.getStatus() == SalaryStatus.PENDING)
                    .findFirst()
                    .ifPresent(newSalary -> {
                        log.info("Updating salary for collaborator ID: {}", collaborator.getId());
                        salaryRepository.findByCollaboratorAndStatus(collaborator, SalaryStatus.CURRENT)
                                .ifPresent(currentSalary -> {
                                    currentSalary.setStatus(SalaryStatus.ARCHIVED);
                                    salaryRepository.save(currentSalary);
                                    log.info("Current salary set as ARCHIVED");
                                });

                        newSalary.setStatus(SalaryStatus.CURRENT);
                        salaryRepository.save(newSalary);
                        log.info("New salary set as CURRENT");
                    });
        }

    }
}
