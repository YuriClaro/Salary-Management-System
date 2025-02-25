package com.humanit.salary_api.mapper;

import com.humanit.salary_api.dto.SalaryDTO;
import com.humanit.salary_api.model.Salary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SalaryMapper {
    @Mapping(target = "collaboratorId", source = "collaborator.id")
    SalaryDTO toDTO(Salary salary);

    @Mapping(target = "collaborator.id", source = "collaboratorId")
    Salary toEntity(SalaryDTO salaryDTO);

    void updateEntityFromDTO(SalaryDTO salaryDTO, @MappingTarget Salary salary);
}
