package com.example.employees.services;

import com.example.employees.entities.Employee;
import com.example.employees.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        return this.employeeRepository.getAll();
    }

    public List<Employee> getByNameOrLastName(String term) {
        return this.employeeRepository.findByNameOrLastName(term);
    }


    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee save(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee employeeToUpdate = getById(id);
        employeeToUpdate.setName(employee.getName());
        employeeToUpdate.setLastName(employee.getLastName());
        employeeToUpdate.setEmail(employee.getEmail());

        return this.employeeRepository.save(employeeToUpdate);
    }

    @Override
    public void delete(Long id) {
        Employee employee = getById(id);
        employee.setActive(false);
        this.employeeRepository.save(employee);
    }
}
