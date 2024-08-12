package com.nitin.springboot_testing.service;

import com.nitin.springboot_testing.entity.Employee;
import com.nitin.springboot_testing.exception.ResourceNotFoundException;
import com.nitin.springboot_testing.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup(){
//        employeeRepository = Mockito.mock(EmployeeRepository.class);
//        employeeService = new EmployeeServiceImpl(employeeRepository);
     employee = Employee.builder()
                .id(1L)
                .firstName("Nitin")
                .lastName("Kumar")
                .email("nitinkumaar99@gmail.com")
                .build();

    }

    //    @Test
//    public void give_when_then(){
//        // given - precondition or setup
//
//        //when - action or the behaviour that we are going test
//
//        // then - verify the output
//
//    }
    @Test
   // @MockitoSettings(strictness = Strictness.LENIENT)
    public void giveEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
        // given - precondition or setup


    //    given(employeeRepository
      //          .findByEmail(employee.getEmail())).willReturn(Optional.empty());
       given(employeeRepository.save(employee)).willReturn(employee);
        System.out.println(employeeRepository);
        System.out.println(employeeService);
        //when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.save(employee);
        System.out.println(savedEmployee);



        // then - verify the output
        assertThat(savedEmployee).isNotNull();
     //   verify(employeeRepository, times(1)).save(employee);

    }
    @Test
    public void giveEmployeeObject_whenThrowException_thenReturnEmployeeObject(){
        // given - precondition or setup


            given(employeeRepository
                  .findByEmail(employee.getEmail())).willReturn(Optional.of(employee));
//        given(employeeRepository.save(employee)).willReturn(employee);
        System.out.println(employeeRepository);
        System.out.println(employeeService);
        //when - action or the behaviour that we are going test
        Assertions.assertThrows(ResourceNotFoundException.class,()->{
           employeeService.saveEmployee(employee);
        });
     //   Employee savedEmployee = employeeRepository.save(employee);
      //  System.out.println(savedEmployee);



        // then - verify the output
    //    assertThat(savedEmployee).isNotNull();
        //   verify(employeeRepository, times(1)).save(employee);
        verify(employeeRepository , never()).save(any(Employee.class));

    }
    // Junit test for getAllEmployees method
    @DisplayName("Junit test for getAllEmployees method")
    @Test
    public void give_whenFindAll_thenListEmployee(){
        // given - precondition or setup
    Employee employee2 = Employee.builder()
                .id(2L)
                .firstName("Atul")
                .lastName("Kumar")
                .email("atul@gmail.com")
                .build();
        given(employeeRepository.findAll()).willReturn(List.of(employee,employee2));

        //when - action or the behaviour that we are going test

        List<Employee> employeeList=employeeService.getAllEmployee();

        // then - verify the output

        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);


    }
    @DisplayName("Junit test for getAllEmployees method in negative scenario")
    @Test
    public void givenEmptyEmployeeList_whenFindAll_thenReturnEmptyListEmployee(){
        // given - precondition or setup
        Employee employee2 = Employee.builder()
                .id(2L)
                .firstName("Atul")
                .lastName("Kumar")
                .email("atul@gmail.com")
                .build();
        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        //when - action or the behaviour that we are going test

        List<Employee> employeeList=employeeService.getAllEmployee();

        // then - verify the output

        assertThat(employeeList).isEmpty();
        assertThat(employeeList.size()).isEqualTo(0);


    }

    //Junit test for getEmployeeById
    @Test
    public void givenEmployee_whenFindById_thenReturnEmployee(){
        // given - precondition or setup
        Employee employee2 = Employee.builder()
                .id(2L)
                .firstName("Atul")
                .lastName("Kumar")
                .email("atul@gmail.com")
                .build();
        given(employeeRepository.findById(employee.getId())).willReturn(Optional.ofNullable(employee));

        //when - action or the behaviour that we are going test

        Employee savedEmployee =employeeService.getEmployeeById(employee.getId()).get();

        // then - verify the output

        assertThat(savedEmployee).isNotNull();
//        assertThat(savedEmployee);


    }
    // Junit test case for updateEmployee
    @Test
    public void givenEmployeeAndId_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        // given - precondition or setup
        Employee employee2 = Employee.builder()
                .id(2L)
                .firstName("Atul")
                .lastName("Kumar")
                .email("atul@gmail.com")
                .build();
        given(employeeRepository.save(employee)).willReturn(employee);



        //when - action or the behaviour that we are going test
        employee.setEmail("nitinjoy99@gmil.com");
        employee.setLastName("Prajapati");



        Employee savedEmployee =employeeService.updateeEmployee(employee);

        // then - verify the output

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getEmail()).isEqualTo("nitinjoy99@gmil.com");


    }

}
