package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
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
    @ApiOperation(value = "Find all employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "{employeeId}")
    @ApiOperation(value = "Find employee by id",
            notes = "Provide an id to look up employee",
            response = Employee.class)
    @ApiResponse(code = 404, message = "Not found")
    public Employee getEmployee(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    @ApiOperation(value = "Create a new employee")
    public Employee newEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.newEmployee(employee);
    }

    @DeleteMapping(path = "{employeeId}")
    @ApiOperation(value = "Delete employee by id")
    @ApiResponse(code = 404, message = "Not found")
    public Long deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);

        return employeeId;
    }

    @PutMapping(path = "{employeeId}")
    @ApiResponse(code = 404, message = "Not found")
    public Employee updateEmployee(@PathVariable Long employeeId,
                                   @Valid @RequestBody Employee employeeToUpdate) {
        Employee employee = employeeService.updateEmployee(employeeId, employeeToUpdate);
        return employee;
    }

    @GetMapping(path = "search")
    @ApiOperation(value = "Search employee",
            notes = "Provide an part of first name and last name to find all matching employees")
    public List<Employee> searchByFirstAndOrLastName(@RequestParam(required = false) String firstName,
                                                     @RequestParam(required = false) String lastName) {
        return employeeService.searchByFirstAndOrLastName(firstName, lastName);
    }
}
