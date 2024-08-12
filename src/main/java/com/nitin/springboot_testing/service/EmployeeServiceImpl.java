package com.nitin.springboot_testing.service;

import com.nitin.springboot_testing.entity.Employee;
import com.nitin.springboot_testing.exception.ResourceNotFoundException;
import com.nitin.springboot_testing.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeServiceImpl  implements EmployeeService{

    private EmployeeRepository employeeRepository;

  // save Employee Method
    @Override
    public Employee saveEmployee(Employee employee) {
       Optional<Employee> getEmployee= employeeRepository.findByEmail(employee.getEmail());
       if(getEmployee.isPresent()){
           throw new ResourceNotFoundException("Employee is already exist with given email :"+employee.getEmail());
       }
       Employee savedEmployee =  employeeRepository.save( employee);
        return savedEmployee;
    }

    @Override
    public List<Employee> getAllEmployee() {
       List<Employee>  employeeList =employeeRepository.findAll();
        return employeeList;
    }

    @Override
    public Optional <Employee> getEmployeeById(long id) {
        Optional<Employee> employee= employeeRepository.findById(id);
        return employee;
    }

    @Override
    public Employee updateeEmployee( Employee updatedEmployee) {

//        Employee employee1  = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("NOT fOUND"));
//         employee1.setFirstName(employee.getFirstName());
//         employee1.setLastName(employee1.getLastName());
//         employee1.setEmail(employee1.getEmail());
         Employee savedEmployee = employeeRepository.save(updatedEmployee);
        return updatedEmployee;
    }
}
