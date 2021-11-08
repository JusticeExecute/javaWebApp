package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getEmployees() {
        return employeeDao.listEmployees();
    }

    public Employee getEmployeeById(Long id) {
        return employeeDao.getEmployee(id);
    }

    public Employee newEmployee(Employee employee) {
        if (employeeDao.getEmployee(employee.getEmployeeId()) != null) {
            throw new IllegalStateException("such an id is reserved");
        }
        employeeDao.create(employee);
        return employee;
    }

    public boolean deleteEmployee(Long employeeId) {
        if (employeeDao.getEmployee(employeeId) == null) {
            throw new IllegalStateException("Employee with ID = " + employeeId + "doesn't exists");
        }
        employeeDao.delete(employeeId);
        return true;
    }

    public Employee updateEmployee(Long employeeId, Employee employeeToUpdate) {
        Employee employee = employeeDao.getEmployee(employeeId);
        if (employee == null) {
            throw new IllegalStateException("Employee with ID = " + employeeId + "doesn't exists");
        }
        employeeDao.update(employeeId, employeeToUpdate);
        employee.setFirstName(employeeToUpdate.getFirstName());
        employee.setGender(employeeToUpdate.getGender());
        return employee;
    }
}
