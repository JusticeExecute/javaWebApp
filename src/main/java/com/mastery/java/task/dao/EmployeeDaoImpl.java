package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void create(Employee employee) {
        String sql = "insert into Employee (employee_id, first_name, gender) values (?, ?, ?)";
        jdbcTemplateObject.update(sql, employee.getEmployeeId(), employee.getFirstName(), employee.getGender().toString());
        System.out.println("Created Record Name = " + employee.getFirstName());
    }

    @Override
    public Employee getEmployee(Long employeeId) {
        String sql = "select * from Employee where employee_id = ?";
        try {
            return jdbcTemplateObject.queryForObject(sql, new EmployeeMapper(), employeeId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Record with ID = " + employeeId + "doesn't exists");
            return null;
        }
    }

    @Override
    public List<Employee> listEmployees() {
        String sql = "select * from Employee";
        return jdbcTemplateObject.query(sql, new EmployeeMapper());
    }

    @Override
    public void delete(Long employeeId) {
        String sql = "delete from Employee where employee_id = ?";
        jdbcTemplateObject.update(sql, employeeId);
        System.out.println("Deleted Record with ID = " + employeeId);
    }

    @Override
    public void update(Long employeeId, Employee employeeToUpdate) {
        String sql = "update Employee set first_name = ?, gender = ?  where employee_id = ?";
        jdbcTemplateObject.update(sql, employeeToUpdate.getFirstName(), employeeToUpdate.getGender().toString(), employeeId);
        System.out.println("Updated Record with ID = " + employeeId);
    }
}
