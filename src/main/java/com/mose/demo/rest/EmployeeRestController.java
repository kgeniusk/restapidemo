package com.mose.demo.rest;

import com.mose.demo.dao.EmployeeRepository;
import com.mose.demo.entity.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    private final EmployeeRepository employeeRepository;

    public EmployeeRestController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping()
    public List<Employee> listEmployee() {
        return (List<Employee>) this.employeeRepository.findAll();
    }

    @PostMapping()
    public Employee createEmployee(@RequestBody Employee employee) {
        if (this.employeeRepository.findById(employee.getId()).isEmpty()) {
            throw new IllegalArgumentException("Employee id already exists");
        }
        return this.employeeRepository.save(employee);
    }

    @PutMapping()
    public Employee updateEmployee(@RequestBody Employee employee) {
        if (employee.getId() == 0) {
            throw new IllegalArgumentException("Employee id is required");
        }
        if (this.employeeRepository.findById(employee.getId()).isEmpty()) {
            throw new IllegalArgumentException("Employee does not exist");
        }
        return this.employeeRepository.save(employee);
    }

    @GetMapping("/{employeeId}")
    public Optional<Employee> getEmployeeById(@PathVariable Integer employeeId) {
        return this.employeeRepository.findById(employeeId);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployeeById(@PathVariable Integer employeeId) {
        this.employeeRepository.deleteById(employeeId);
        return "Employee deleted successfully";
    }
}
