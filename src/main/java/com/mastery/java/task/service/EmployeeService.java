package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
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

    public boolean deleteEmployee(Long employee_id) {
        if (employeeDao.getEmployee(employee_id) == null) {
            throw new IllegalStateException("Employee with ID = " + employee_id + "doesn't exists");
        }
        employeeDao.delete(employee_id);
        return true;
    }

    public Employee updateEmployee(Long employee_id, String first_name, Gender gender) {
        Employee employee = employeeDao.getEmployee(employee_id);
        if (employee == null) {
            throw new IllegalStateException("Employee with ID = " + employee_id + "doesn't exists");
        }
        employeeDao.update(employee_id, first_name, gender);
        employee.setFirstName(first_name);
        employee.setGender(gender);
        return employee;
    }
}
