package com.udacity.jdnd.course3.critter.employee;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);
    EmployeeDTO map(Employee Employee);
    Employee map(EmployeeDTO EmployeeDTO);
    List<EmployeeDTO> map(List<Employee> Employees);
}
