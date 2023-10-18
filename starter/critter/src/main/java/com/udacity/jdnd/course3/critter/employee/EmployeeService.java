package com.udacity.jdnd.course3.critter.employee;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeDTO save(EmployeeDTO employeeDTO){
        Employee employee = employeeMapper.map(employeeDTO);
        employee = employeeRepository.save(employee);
        return employeeMapper.map(employee);
    }

    public EmployeeDTO getEmployeeById(Long employeeId){
        return employeeMapper.map(employeeRepository.findById(employeeId).get());
    }
    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId){
        employeeRepository.findById(employeeId).get().setDaysAvailable(daysAvailable);
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        Set<EmployeeSkill> skills = employeeDTO.getSkills();
        LocalDate date = employeeDTO.getDate();
        List<Employee> available = employeeRepository.getAllByDaysAvailableContains(date.getDayOfWeek());
        List<EmployeeDTO> availableAndSkilled = available.stream().filter(employee -> employee.getSkills().containsAll(skills)).map(employee -> employeeMapper.map(employee))
                .collect(Collectors.toList());
        return availableAndSkilled;
    }
}
