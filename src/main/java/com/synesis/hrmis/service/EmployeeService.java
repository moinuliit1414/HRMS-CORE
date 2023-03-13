package com.synesis.hrmis.service;

import com.synesis.hrmis.domain.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    ResponseEntity<?> saveEmployee(Employee employeeInfo);
    ResponseEntity<?> getEmployeeById(long employeeId);
    ResponseEntity<?> getAllEmployee();
    ResponseEntity<?> updateEmployee(Employee employeeId);
    ResponseEntity<?> deleteEmployee(long employeeId);
    ResponseEntity<?> getAllEmployeeName();

}
