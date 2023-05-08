package com.example.employees.controllers;

import com.example.employees.entities.Employee;
import com.example.employees.services.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/employees")

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = this.employeeService.getAll();
        return employees.isEmpty() ?
                ResponseEntity.noContent().build() : ResponseEntity.ok(employees);
    }

    @GetMapping("show/{id}")
    public ResponseEntity<Employee> show(@PathVariable Long id) {
        Employee employee = this.employeeService.getById(id);
        return employee == null?
                ResponseEntity.notFound().build() : ResponseEntity.ok(employee);
    }
    @GetMapping("search/{term}")
    public ResponseEntity<List<Employee>> search(@PathVariable String term) {
        List<Employee> employees = this.employeeService.getByNameOrLastName(term);
        return employees.isEmpty() ?
                ResponseEntity.noContent().build() : ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<Employee> store(@RequestBody Employee employeeRequest) {
        Employee employee = this.employeeService.save(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee employeeRequest) {
        if(id != employeeRequest.getId()) ResponseEntity.badRequest().build();
        Employee employee = this.employeeService.update(id, employeeRequest);
        return  ResponseEntity.ok(employee);
    }

    @DeleteMapping("{id}")
    public ResponseEntity destroy(@PathVariable Long id) {
        Employee employee = this.employeeService.getById(id);
        if (employee == null ) ResponseEntity.notFound().build();
        this.employeeService.delete(id);
        return ResponseEntity.ok().build();
    }

}
