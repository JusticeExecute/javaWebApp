package com.mastery.java.task.service;

import com.mastery.java.task.config.AppConfiguration;
import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

//@SpringBootTest(classes = EmployeeServiceTest.class)
@Import(AppConfiguration.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeDao employeeDao;

    @Test
    public void testFindAll() {
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Alex", Gender.MALE),
                new Employee(2L, "Lex", Gender.MALE));

        when(employeeDao.listEmployees()).thenReturn(employees);

        List<Employee> result = employeeService.getEmployees();

        Assertions.assertEquals(result, employees);
    }

    @Test
    public void testFindById() {
        Employee employee = new Employee(2L, "Lex", Gender.MALE);

        when(employeeDao.getEmployee(employee.getEmployeeId())).thenReturn(employee);

        Assertions.assertEquals(employeeService.getEmployeeById(employee.getEmployeeId()), employee);
    }
}
