package com.synesis.hrmis.service;

import org.springframework.http.ResponseEntity;

public interface EmployeeTypeService {
    ResponseEntity<?> getAllEmployeeType();

    ResponseEntity<?> getEmployeeTypeById(long employeeTypeId);

    ResponseEntity<?> saveEmployeeType(Object employeeTypeInfo);

    ResponseEntity<?> updateEmployeeType(long employeeTypeId, Object employeeTypeInfo);

    ResponseEntity<?> deleteEmployeeType(long employeeTypeId);

}
