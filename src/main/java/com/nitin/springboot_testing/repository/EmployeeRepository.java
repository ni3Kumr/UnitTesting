package com.nitin.springboot_testing.repository;

import com.nitin.springboot_testing.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

   Optional<Employee> findByEmail(String email);

   // Implementing JPQL query with index params
   @Query("Select e  from Employee e where e.firstName =?1 and e.lastName = ?2")
   Employee findJPQL(String firstName, String lastName);

   //
   // Implementing JPQL query with named params
   @Query("Select e  from Employee e where e.firstName =:firstName and e.lastName =:lastName ")
   Employee findJPQLNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

   @Query(value = "select * from employees e where e.first_name=?1 and e.last_name= ?2", nativeQuery = true)
   Employee findByNativeSQL(String firstName, String lastName);

}
