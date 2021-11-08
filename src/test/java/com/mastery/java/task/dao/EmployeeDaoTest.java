package com.mastery.java.task.dao;

import com.mastery.java.task.config.AppConfiguration;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = EmployeeDaoTest.class)
@Import(AppConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeDaoTest {
    @Autowired
    private EmployeeDao employeeDao;

    @Test
    @Transactional
    @Rollback(true)
    public void testCreateEmployee() {
        Employee employee = new Employee(9L, "Test user", Gender.FEMALE);
        employeeDao.create(employee);

        Assert.assertEquals(employee, employeeDao.getEmployee(employee.getEmployeeId()));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetEmployeeById() {
        Employee employee = new Employee(9L, "Test user", Gender.FEMALE);
        employeeDao.create(employee);

        Assert.assertEquals(employee, employeeDao.getEmployee(employee.getEmployeeId()));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateEmployee() {
        Employee employee = new Employee(9L, "Test user", Gender.FEMALE);
        employeeDao.create(employee);
        employee.setFirstName("Test user updated");
        employee.setGender(Gender.MALE);
        employeeDao.update(employee.getEmployeeId(), employee.getFirstName(), employee.getGender());

        Assert.assertEquals(employee, employeeDao.getEmployee(employee.getEmployeeId()));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteEmployee() {
        Employee employee = new Employee(9L, "Test user", Gender.FEMALE);
        employeeDao.create(employee);
        employeeDao.delete(employee.getEmployeeId());

        Assert.assertNull(employeeDao.getEmployee(employee.getEmployeeId()));
    }
}
