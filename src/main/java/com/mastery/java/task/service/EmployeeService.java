package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.MyServiceNotFoundException;
import com.mastery.java.task.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    protected static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            log.error("Employee with ID = " + employeeId + " doesn't exists");
            throw new MyServiceNotFoundException("Employee with ID = " + employeeId + " doesn't exists");
        }
        return employeeRepository.getById(employeeId);
    }

    public Employee newEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public boolean deleteEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            log.error("Employee with ID = " + employeeId + " doesn't exists");
            throw new MyServiceNotFoundException("Employee with ID = " + employeeId + " doesn't exists");
        }
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public Employee updateEmployee(Long employeeId, Employee employeeToUpdate) {
        Employee employee = employeeRepository.getById(employeeId);
        if (!employeeRepository.existsById(employeeId)) {
            log.error("Employee with ID = " + employeeId + " doesn't exists");
            throw new MyServiceNotFoundException("Employee with ID = " + employeeId + " doesn't exists");
        }
        employee.setFirstName(employeeToUpdate.getFirstName());
        employee.setGender(employeeToUpdate.getGender());
        employeeRepository.save(employee);
        log.debug("Employ saved to DD");
        return employee;
    }

    public List<Employee> searchByFirstAndOrLastName(String firstName, String lastName) {
        return employeeRepository.searchByFirstAndOrLastName(firstName, lastName);
    }
}
