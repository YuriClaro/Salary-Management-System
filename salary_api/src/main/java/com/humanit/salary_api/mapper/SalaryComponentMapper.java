package com.humanit.salary_api.mapper;

import com.humanit.salary_api.dto.SalaryComponentDTO;
import com.humanit.salary_api.model.SalaryComponent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SalaryComponentMapper {
    @Mapping(target = "salaryId", source = "salary.id")
    SalaryComponentDTO toDTO(SalaryComponent salaryComponent);

    @Mapping(target = "salary.id", source = "salaryId")
    SalaryComponent toEntity(SalaryComponentDTO salaryComponentDTO);

    void updateEntityFromDTO(SalaryComponentDTO salaryComponentDTO, @MappingTarget SalaryComponent salaryComponent);
}
