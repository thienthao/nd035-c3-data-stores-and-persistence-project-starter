package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.enumeration.EmployeeSkill;
import com.udacity.jdnd.course3.critter.model.Employee;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Employee getEmployeeById(Long id);

    void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId);

    List<Employee> findEmployeeForService(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek);
}
