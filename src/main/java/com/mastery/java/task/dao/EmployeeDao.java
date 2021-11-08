package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;

import java.util.List;

public interface EmployeeDao {
    void create(Employee employee);

    Employee getEmployee(Long employeeId);

    List<Employee> listEmployees();

    void delete(Long employeeId);

    void update(Long employeeId, Employee employeeToUpdate);

}
