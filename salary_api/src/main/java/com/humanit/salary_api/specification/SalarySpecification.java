package com.humanit.salary_api.specification;

import com.humanit.salary_api.enumerator.SalaryStatus;
import com.humanit.salary_api.model.Salary;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class SalarySpecification implements Specification<Salary> {
    private List<SalaryStatus> status;
    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public Predicate toPredicate(Root<Salary> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(this.status)) {
            predicates.add(root.get("status").in(status));
        }
        if (Objects.nonNull(this.startDate) && Objects.nonNull(this.endDate)) {
            predicates.add(criteriaBuilder.between(root.get("effectiveDate"), this.startDate, this.endDate));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
