package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
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
    @ApiResponse(code = 500, message = "Internal server error")
    public List<Employee> getEmployees() {
        List<Employee> employeeList = employeeService.getEmployees();
        log.info("OUT: getEmployees - list size {}", employeeList.size());
        return employeeService.getEmployees();
    }

    @GetMapping(path = "{employeeId}")
    @ApiOperation(value = "Find employee by id",
            notes = "Provide an id to look up employee",
            response = Employee.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public Employee getEmployeeById(@Min(1) @Max(9999999) @PathVariable Long employeeId) {
        log.info("IN: getEmployeeById - {}", employeeId);
        Employee employee = employeeService.getEmployeeById(employeeId);
        log.info("OUT: getEmployeeById - {}", employee);
        return employee;
    }

    @PostMapping
    @ApiOperation(value = "Create a new employee")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public Employee newEmployee(@Valid @RequestBody Employee employee) {
        log.info("IN: newEmployee - {}", employee);
        Employee newEmployee = employeeService.newEmployee(employee);
        log.info("OUT: newEmployee - {}", newEmployee);
        return newEmployee;
    }

    @DeleteMapping(path = "{employeeId}")
    @ApiOperation(value = "Delete employee by id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public Long deleteEmployee(@Min(1) @Max(9999999) @PathVariable Long employeeId) {
        log.info("IN: deleteEmployee - {}", employeeId);
        employeeService.deleteEmployee(employeeId);
        log.info("OUT: deleteEmployee - {}", employeeId);
        return employeeId;
    }

    @PutMapping(path = "{employeeId}")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public Employee updateEmployee(@Min(1) @Max(9999999) @PathVariable Long employeeId,
                                   @Valid @RequestBody Employee employeeToUpdate) {
        log.info("IN: updateEmployee - employeeId = {}, employeeToUpdate = {}", employeeId, employeeToUpdate);
        Employee employee = employeeService.updateEmployee(employeeId, employeeToUpdate);
        log.info("OUT: updateEmployee - {}", employee);
        return employee;
    }

    @GetMapping(path = "/")
    @ApiOperation(value = "Search employee",
            notes = "Provide an part of first name and last name to find all matching employees")
    @ApiResponse(code = 500, message = "Internal server error")
    public List<Employee> searchByFirstAndOrLastName(@RequestParam String firstName,
                                                     @RequestParam String lastName) {
        log.info("IN: searchByFirstAndOrLastName - firstName = {}, lastName = {}", firstName, lastName);
        List<Employee> employeeList = employeeService.searchByFirstAndOrLastName(firstName, lastName);
        log.info("OUT: searchByFirstAndOrLastName - list size {}", employeeList.size());
        return employeeList;
    }
}
