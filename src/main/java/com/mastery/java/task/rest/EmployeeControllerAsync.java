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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(path = "api/v1/employees/async")
public class EmployeeControllerAsync {
    protected static final Logger log = LoggerFactory.getLogger(EmployeeControllerAsync.class);

    private EmployeeService employeeService;

    @Autowired
    public EmployeeControllerAsync(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ApiOperation(value = "Create a new employee")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public void newEmployeeAsync(@Valid @RequestBody Employee employee) {
        log.info("IN: newEmployee - {}", employee);
        employeeService.newEmployeeAsync(employee);
        log.info("Employee sent to jms queue");
    }
}
