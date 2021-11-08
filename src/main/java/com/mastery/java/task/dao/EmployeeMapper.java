package com.mastery.java.task.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeMapper implements RowMapper<Employee> {
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Employee(rs.getLong("employee_id"), rs.getString("first_name"), Gender.byId(rs.getInt("gender")));
    }
}