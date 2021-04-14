package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.repositories.EmployeesRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeesRepository employeesRepository;

    public Employee getEmployeeById(Long id) { return employeesRepository.getOne(id); }

    public Employee saveEmployee(Employee employee) {
        return employeesRepository.saveAndFlush(employee);
    }

    public Employee setEmployeeAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Employee employee = employeesRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
        return employeesRepository.saveAndFlush(employee);
    }

    public List<Employee> getEmployeesByAvailabilityAndSkills(LocalDate date, Set<EmployeeSkill> skills) {
        return employeesRepository.getAllEmployeesByDaysAvailable(date.getDayOfWeek()).stream().filter(employee ->
                employee.getSkills().containsAll(skills)).collect(Collectors.toList());
    }
}