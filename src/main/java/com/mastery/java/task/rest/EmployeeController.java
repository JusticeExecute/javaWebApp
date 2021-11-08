package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "{employee_id}")
    public Employee getEmployee(@PathVariable("employee_id") Long employee_id) {
        return employeeService.getEmployeeById(employee_id);
    }

    @PostMapping
    public ResponseEntity<Employee> newEmployee(@RequestBody Employee employee) {
        Employee employee1 = employeeService.newEmployee(employee);
        return new ResponseEntity<>(employee1, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{employee_id}")
    public ResponseEntity<Long> deleteEmployee(@PathVariable("employee_id") Long employee_id){
        employeeService.deleteEmployee(employee_id);

        return new ResponseEntity<>(employee_id, HttpStatus.OK);
    }

    @PutMapping(path = "{employee_id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("employee_id") Long employee_id,
                               @RequestParam String first_name,
                               @RequestParam Gender gender){
        Employee employee = employeeService.updateEmployee(employee_id, first_name, gender);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
