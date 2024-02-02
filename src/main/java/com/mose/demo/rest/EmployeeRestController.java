package com.mose.demo.rest;

import com.mose.demo.dao.EmployeeRepository;
import com.mose.demo.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    private EmployeeRepository employeeRepository;

    public EmployeeRestController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping()
    public List<Employee> listEmployee() {
        return (List<Employee>) this.employeeRepository.findAllByOrderByLastName();
    }

    @GetMapping("/{employeeId}")
    public Optional<Employee> getEmployeeById(@PathVariable Integer employeeId) {
        return this.employeeRepository.findById(employeeId);
    }

    @PutMapping()
    public Employee updateEmployee(@RequestBody Employee employee) {
        if (this.employeeRepository.findById(employee.getId()).isPresent()) {
            return this.employeeRepository.save(employee);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId) {
        this.employeeRepository.deleteById(employeeId);
    }
}
