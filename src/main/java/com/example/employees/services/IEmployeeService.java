package com.example.employees.services;

import com.example.employees.entities.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEmployeeService {

    public List<Employee> getAll();
    public Employee getById(Long id);
    public Employee save(Employee employee);
    public Employee update(Long id, Employee employee);
    public void delete(Long id);
}
