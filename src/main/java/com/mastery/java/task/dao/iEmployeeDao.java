package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;

import java.util.List;

public interface iEmployeeDao {
    public void create(Employee employee);

    public Employee getEmployee(Long employee_id);

    public List listEmployees();

    public void delete(Long employee_id);

    public void update(Long employee_id, String first_name, Gender gender);

}
