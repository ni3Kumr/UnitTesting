package com.nitin.springboot_testing.repository;

import com.nitin.springboot_testing.entity.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {
    // given - when - then
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;




    @BeforeEach
    public void setUp(){
         employee = Employee.builder()
                .firstName("Nitin")
                .lastName("Kumar")
                .email("nitinkumaar9193@gmail.com")
                .build();

    }

    // junit test case for save employee operations
    @DisplayName("Junit test for save employee operation")
    @Test

    public void givenEmployeeObject_whenSave_thenReturnedSavedEmployee(){

        // given - precondition or setup

        //when - action or the behaviour that we are going test

        Employee savedEmployee = employeeRepository.save(employee);


        // then - verify the output
       assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);





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
    // junit test case for  getAll Employee testCases4
     @DisplayName("junit test case for  getAll Employee ")
    @Test
    public void giveEmployeesList_whenFindAll_thenEmployeeList(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Nitin")
//                .lastName("Kumar")
//                .email("nitinkumaar9193@gmail.com")
//                .build();
        Employee employee1 = Employee.builder()
                .firstName("Atul")
                .lastName("Kumar")
                .email("atul@gmail.com")
                .build();

        employeeRepository.save(employee);

        employeeRepository.save(employee1);


        //when - action or the behaviour that we are going test
        List<Employee> employeeList = employeeRepository.findAll();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }

    // Junit test case for find Employee By id

    @Test
    public void giveEmployee_whenFindById_thenEmployee(){
        // given - precondition or setup

//            Employee employee = Employee.builder()
//                    .firstName("Nitin")
//                    .lastName("Kumar")
//                    .email("nitinkumaar9193@gmail.com")
//                    .build();
            Employee employee1 = Employee.builder()
                    .firstName("Atul")
                    .lastName("Kumar")
                    .email("atul@gmail.com")
                    .build();

            employeeRepository.save(employee);

            employeeRepository.save(employee1);

        //when - action or the behaviour that we are going test

            Employee savedEmployee = employeeRepository.findById(employee.getId()).get();


        // then - verify the output
            assertThat(savedEmployee.getId()).isEqualTo(employee.getId());
            assertThat(savedEmployee).isNotNull();

    }
    // Unit test case for find Employee By Email;

    @Test
    public void givenEmployee_whenFindByEmail_thenEmployeeObject(){
        // given - precondition or setup

//            Employee employee = Employee.builder()
//                    .firstName("Nitin")
//                    .lastName("Kumar")
//                    .email("nitinkumaar9193@gmail.com")
//                    .build();
            Employee employee1 = Employee.builder()
                    .firstName("Atul")
                    .lastName("Kumar")
                    .email("atul@gmail.com")
                    .build();

            employeeRepository.save(employee);

            employeeRepository.save(employee1);



        //when - action or the behaviour that we are going test

            Employee resultEmployee = employeeRepository.findByEmail(employee1.getEmail()).get();


        // then - verify the output
            assertThat(resultEmployee).isNotNull();
            assertThat(resultEmployee.getEmail()).isEqualTo(employee1.getEmail());


    }
    // Unit Test case For update Employee

    @Test
    public void giveEmployeeObject_whenUpdateEmployee_thenEmployeeObject(){
        // given - precondition or setup
//            Employee employee = Employee.builder()
//                    .firstName("Nitin")
//                    .lastName("Kumar")
//                    .email("nitinkumaar9193@gmail.com")
//                    .build();
            Employee employee1 = Employee.builder()
                    .firstName("Atul")
                    .lastName("Kumar")
                    .email("atul@gmail.com")
                    .build();

            employeeRepository.save(employee);

            employeeRepository.save(employee1);

        //when - action or the behaviour that we are going test
            Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
            savedEmployee.setEmail("nitinjoy99@gmail.com");
            savedEmployee.setLastName("Prajapati");
        // then - verify the output

            Employee resultedEmployee  =employeeRepository.save(savedEmployee);
            assertThat(resultedEmployee).isNotNull();
            assertThat(resultedEmployee.getEmail()).isEqualTo(employee.getEmail());



    }
    // unit test case for delete Employee

    @Test
    public void giveEmployeeObject_whenDelete_thenNullObject(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Nitin")
//                .lastName("Kumar")
//                .email("nitinkumaar9193@gmail.com")
//                .build();
        Employee employee1 = Employee.builder()
                .firstName("Atul")
                .lastName("Kumar")
                .email("atul@gmail.com")
                .build();

        employeeRepository.save(employee);

        employeeRepository.save(employee1);


        //when - action or the behaviour that we are going test


        employeeRepository.delete(employee);
        Optional<Employee> employeeDb= employeeRepository.findById(employee.getId());

        // then - verify the output

        assertThat(employeeDb).isEmpty();

    }

    // unit test case for costume Query using JQL with index

    @Test
    public void givenFirstNameLastName_whenFindJPQL_thenEmployeeObject(){
//        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Nitin")
//                .lastName("Kumar")
//                .email("nitinkumaar9193@gmail.com")
//                .build();
//        Employee employee1 = Employee.builder()
//                .firstName("Atul")
//                .lastName("Kumar")
//                .email("atul@gmail.com")
//                .build();

        employeeRepository.save(employee);

//        employeeRepository.save(employee1);



        //when - action or the behaviour that we are going test

      Employee employeeDB =   employeeRepository.findJPQL("Nitin","Kumar");

        // then - verify the output
        assertThat(employeeDB).isNotNull();
        assertThat(employeeDB.getFirstName()).isEqualTo(employee.getFirstName());


    }
    // unit test case for costume Query using JQL with  named Parameter
    @DisplayName(" unit test case for costume Query using JQL with  named Parameter\n")
    @Test
    public void givenFirstNameLastName_whenFindByNamedParams_thenEmployeeObject(){
        // given - precondition or setup

        employeeRepository.save(employee);




        //when - action or the behaviour that we are going test

        Employee employeeDB =   employeeRepository.findJPQLNamedParams("Nitin","Kumar");

        // then - verify the output
        assertThat(employeeDB).isNotNull();
        assertThat(employeeDB.getFirstName()).isEqualTo(employee.getFirstName());


    }
    @DisplayName(" unit test case for costume Query using SQLNative Query")
    @Test
    public void givenFirstNameLastName_whenFindBySQLNative_thenEmployeeObject(){
        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Nitin")
                .lastName("Kumar")
                .email("nitinkumaar9193@gmail.com")
                .build();
        Employee employee1 = Employee.builder()
                .firstName("Atul")
                .lastName("Kumar")
                .email("atul@gmail.com")
                .build();

        employeeRepository.save(employee);

        employeeRepository.save(employee1);



        //when - action or the behaviour that we are going test

        Employee employeeDB =   employeeRepository.findByNativeSQL("Nitin","Kumar");

        // then - verify the output
        assertThat(employeeDB).isNotNull();
        assertThat(employeeDB.getFirstName()).isEqualTo(employee.getFirstName());


    }






}
