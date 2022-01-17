package com.mastery.java.task.repository;

import com.mastery.java.task.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE (:firstName != '' AND e.firstName LIKE CONCAT('%',:firstName,'%'))" +
            " OR (:lastName != '' AND e.lastName LIKE CONCAT('%',:lastName,'%'))")
    List<Employee> searchByFirstAndOrLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
