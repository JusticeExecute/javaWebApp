package com.mastery.java.task.dto;


import com.mastery.java.task.validator.Adult;
import com.mastery.java.task.validator.EnumNamePattern;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table
public class Employee {
    @Id
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @Column(name = "employee_id", unique = true, nullable = false)
    @ApiModelProperty(notes = "The unique id of employee")
    private Long employeeId;
    @Column(name = "first_name")
    @ApiModelProperty(notes = "Employee first name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 15, message = "Name should be between 2 and 15 characters")
    private String firstName;
    @Column(name = "last_name")
    @ApiModelProperty(notes = "Employee last name")
    @NotEmpty(message = "Lastname should not be empty")
    @Size(min = 2, max = 15, message = "Lastname should be between 2 and 15 characters")
    private String lastName;
    @Column(name = "gender")
    @ApiModelProperty(notes = "Employee gender could only be male or female")
    @Enumerated(EnumType.STRING)
    @EnumNamePattern(anyOf = {Gender.MALE, Gender.FEMALE})
    private Gender gender;
    @Column(name = "age")
    @ApiModelProperty(notes = "Employee age")
    @Max(150)
    @Adult(message = "Age should be greater than 17")
    private int age;

    public Employee() {
    }

    public Employee(Long employeeId, String firstName, String lastName, Gender gender, int age) {
        this(firstName, lastName, gender, age);
        this.employeeId = employeeId;
    }

    public Employee(String firstName, String lastName, Gender gender, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId) && Objects.equals(firstName, employee.firstName) && gender == employee.gender && age == employee.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, firstName, gender, age);
    }
}
