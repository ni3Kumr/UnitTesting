package com.nitin.springboot_testing.service;

import com.nitin.springboot_testing.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployee();
    Optional<Employee> getEmployeeById(long id);
    Employee updateeEmployee(Employee employee);
}
