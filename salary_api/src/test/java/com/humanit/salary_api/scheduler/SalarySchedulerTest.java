package com.humanit.salary_api.scheduler;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.model.Collaborator;
import com.humanit.salary_api.model.Salary;
import com.humanit.salary_api.repository.CollaboratorRepository;
import com.humanit.salary_api.repository.SalaryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SalarySchedulerTest {

    @Mock
    private SalaryRepository salaryRepository;

    @Mock
    private CollaboratorRepository collaboratorRepository;

    @InjectMocks
    private SalaryScheduler salaryScheduler;

    @Test
    void testUpdateSalariesByEffectiveDate() {
        Collaborator collaborator = mock(Collaborator.class);

        Salary currentSalary = new Salary();
        currentSalary.setStatus(SalaryStatus.CURRENT);
        currentSalary.setEffectiveDate(LocalDate.now().minusDays(1));

        Salary pendingSalary = new Salary();
        pendingSalary.setStatus(SalaryStatus.PENDING);
        pendingSalary.setEffectiveDate(LocalDate.now());

        List<Salary> salaries = Arrays.asList(currentSalary, pendingSalary);
        when(collaborator.getSalaries()).thenReturn(salaries);
        when(collaboratorRepository.findAll()).thenReturn(Arrays.asList(collaborator));

        when(salaryRepository.findByCollaboratorAndStatus(collaborator, SalaryStatus.CURRENT))
                .thenReturn(Optional.of(currentSalary));

        salaryScheduler.updateSalariesByEffectiveDate();

        verify(salaryRepository).save(currentSalary);
        verify(salaryRepository).save(pendingSalary);

        assertEquals(SalaryStatus.ARCHIVED, currentSalary.getStatus());
        assertEquals(SalaryStatus.CURRENT, pendingSalary.getStatus());
    }

}
