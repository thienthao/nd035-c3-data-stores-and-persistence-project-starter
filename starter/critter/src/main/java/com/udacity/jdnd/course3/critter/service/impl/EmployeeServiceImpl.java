package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.enumeration.EmployeeSkill;
import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot find Employee with id: " + id));
    }

    @Override
    public void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Employee employee = getEmployeeById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        saveEmployee(employee);
    }

    @Override
    public List<Employee> findEmployeeForService(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {
        List<Employee> employees = employeeRepository.findAllByDaysAvailableAndSkillsIn(dayOfWeek, skills);
        return employees.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
