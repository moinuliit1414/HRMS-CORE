package com.synesis.hrmis.repository;

import com.synesis.hrmis.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(nativeQuery = true, value = "select full_name from sys_employee")
    List<String> findAllEmployeeName();
}
