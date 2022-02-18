package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.MyServiceNotFoundException;
import com.mastery.java.task.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    protected static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
    private EmployeeRepository employeeRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(
                () -> new MyServiceNotFoundException("Employee with ID = " + employeeId + " doesn't exists"));
    }

    public Employee newEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public void newEmployeeAsync(Employee employee) {
        jmsTemplate.convertAndSend("my_queue", employee);
    }

    @JmsListener(destination = "my_queue")
    public void receiveEmployeeFromQueue(Employee employee) {
        log.info("Receive employee from JMS queue - {}", employee);
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId) {
        employeeRepository.delete(employeeRepository.findById(employeeId).orElseThrow(
                () -> new MyServiceNotFoundException("Employee with ID = " + employeeId + " doesn't exists")));
    }

    public Employee updateEmployee(Long employeeId, Employee employeeToUpdate) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new MyServiceNotFoundException("Employee with ID = " + employeeId + " doesn't exists"));
        employee.setFirstName(employeeToUpdate.getFirstName());
        employee.setGender(employeeToUpdate.getGender());
        employee.setAge(employeeToUpdate.getAge());
        employeeRepository.save(employee);
        log.debug("Employ saved to DD");
        return employee;
    }

    public List<Employee> searchByFirstAndOrLastName(String firstName, String lastName) {
        return employeeRepository.findByFirstNameContainingAndLastNameContaining(firstName, lastName);
    }
}
