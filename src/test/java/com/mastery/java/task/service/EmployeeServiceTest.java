package com.mastery.java.task.service;

import com.mastery.java.task.App;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = EmployeeServiceTest.class)
@Import(App.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void testFindAll() {
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Alex", "Vasilko", Gender.MALE, 22),
                new Employee(2L, "Lex", "Raketnik", Gender.MALE, 22));

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getEmployees();

        assertThat(result).isEqualTo(employees);
    }

    @Test
    public void testFindById() {
        Employee employee = new Employee(2L, "Lex", "Lutor", Gender.MALE, 22);

        when(employeeRepository.existsById(employee.getEmployeeId())).thenReturn(true);
        when(employeeRepository.getById(employee.getEmployeeId())).thenReturn(employee);

        assertThat(employeeService.getEmployeeById(employee.getEmployeeId())).isEqualTo(employee);
    }
}
