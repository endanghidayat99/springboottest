package com.endang.springbootapp.repositories;

import com.endang.springbootapp.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {

    @Query("select c from Employee c where c.email = :email")
    Employee findByEmail(@Param("email") String email);

}
