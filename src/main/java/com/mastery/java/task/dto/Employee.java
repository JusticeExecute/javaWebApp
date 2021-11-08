package com.mastery.java.task.dto;


import java.util.Objects;

public class Employee {
    private Long employeeId;
    private String firstName;
    private Gender gender;

    public Employee() {
    }

    public Employee(Long employeeId, String firstName, Gender gender) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.gender = gender;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId) && Objects.equals(firstName, employee.firstName) && gender == employee.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, firstName, gender);
    }
}
