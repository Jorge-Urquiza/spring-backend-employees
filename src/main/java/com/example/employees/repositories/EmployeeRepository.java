package com.example.employees.repositories;

import com.example.employees.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //@Query("SELECT u FROM Employee e WHERE e.name like %:term% or e.lastName like %:term%")
    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:term% or e.lastName LIKE %:term%")
    List<Employee> findByNameOrLastName(@Param("term") String term);

    @Query("SELECT e FROM Employee e WHERE e.isActive = 1")
    List<Employee> getAll();
}
