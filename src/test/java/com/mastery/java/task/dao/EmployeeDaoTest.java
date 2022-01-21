package com.mastery.java.task.dao;

import com.mastery.java.task.App;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = EmployeeDaoTest.class)
@Import(App.class)
@RunWith(SpringJUnit4ClassRunner.class)
class EmployeeDaoTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Transactional
    @Rollback(true)
    void testCreateEmployee() {
        Employee employee = new Employee("Test", "user", Gender.FEMALE, 22);
        employee = employeeRepository.save(employee);

        Assert.assertEquals(employee, employeeRepository.getById(employee.getEmployeeId()));
    }

    @Test
    @Transactional
    @Rollback(true)
    void testGetEmployeeById() {
        Employee employee = new Employee("Test", "user", Gender.FEMALE, 22);
        employee = employeeRepository.save(employee);

        Assertions.assertEquals(employee, employeeRepository.getById(employee.getEmployeeId()));
    }

    @Test
    @Transactional
    @Rollback(true)
    void testUpdateEmployee() {
        Employee employee = new Employee("Test", "user", Gender.FEMALE, 22);
        employee = employeeRepository.save(employee);
        employee.setFirstName("Test user updated");
        employee.setGender(Gender.MALE);
        employee = employeeRepository.save(employee);

        Assertions.assertEquals(employee, employeeRepository.getById(employee.getEmployeeId()));
    }

    @Test
    @Transactional
    @Rollback(true)
    void testDeleteEmployee() {
        Employee employee = new Employee("Test", "user", Gender.FEMALE, 22);
        employee = employeeRepository.save(employee);
        employeeRepository.deleteById(employee.getEmployeeId());

        Assertions.assertFalse(employeeRepository.existsById(employee.getEmployeeId()));
    }
}
