package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class EmployeeDao implements iEmployeeDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public EmployeeDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(Employee employee) {
        String SQL = "insert into Employee (employee_id, first_name, gender) values (?, ?, ?)";
        jdbcTemplateObject.update(SQL, employee.getEmployeeId(), employee.getFirstName(), employee.getGender().getId());
        System.out.println("Created Record Name = " + employee.getFirstName());
    }

    @Override
    public Employee getEmployee(Long employee_id) {
        String SQL = "select * from Employee where employee_id = ?";
        try {
            return jdbcTemplateObject.queryForObject(SQL, new EmployeeMapper(), employee_id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Record with ID = " + employee_id + "doesn't exists");
            return null;
        }
    }

    @Override
    public List<Employee> listEmployees() {
        String SQL = "select * from Employee";
        return jdbcTemplateObject.query(SQL, new EmployeeMapper());
    }

    @Override
    public void delete(Long employee_id) {
        String SQL = "delete from Employee where employee_id = ?";
        jdbcTemplateObject.update(SQL, employee_id);
        System.out.println("Deleted Record with ID = " + employee_id);
    }

    @Override
    public void update(Long employee_id, String first_name, Gender gender) {
        String SQL = "update Employee set first_name = ?, gender = ?  where employee_id = ?";
        jdbcTemplateObject.update(SQL, first_name, gender.getId(), employee_id);
        System.out.println("Updated Record with ID = " + employee_id);
    }
}
