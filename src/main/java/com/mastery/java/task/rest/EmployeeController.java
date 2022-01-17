package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employees")
public class EmployeeController {
    protected static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "{employeeId}")
    public Employee getEmployee(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public ResponseEntity<Employee> newEmployee(@RequestBody Employee employee) {
        Employee employee1 = employeeService.newEmployee(employee);
        return new ResponseEntity<>(employee1, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{employeeId}")
    public ResponseEntity<Long> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);

        return new ResponseEntity<>(employeeId, HttpStatus.OK);
    }

    @PutMapping(path = "{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long employeeId,
                                                   @RequestBody Employee employeeToUpdate) {
        Employee employee = employeeService.updateEmployee(employeeId, employeeToUpdate);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping(path = "search")
    public List<Employee> searchByFirstAndOrLastName(@RequestParam(required = false) String firstName,
                                                     @RequestParam(required = false) String lastName) {
        return employeeService.searchByFirstAndOrLastName(firstName, lastName);
    }
}
