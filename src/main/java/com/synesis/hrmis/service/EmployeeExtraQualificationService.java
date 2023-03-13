package com.synesis.hrmis.service;

import org.springframework.http.ResponseEntity;

public interface EmployeeExtraQualificationService {
    ResponseEntity<?> getAllEmployeeExtraQualification();

    ResponseEntity<?> getAllEmployeeExtraQualificationById(long employeeExtraQualificationId);

    ResponseEntity<?> saveEmployeeExtraQualification(Object employeeExtraQualificationInfo);

    ResponseEntity<?> updateEmployeeExtraQualification(long employeeExtraQualificationId, Object employeeExtraQualificationInfo);

    ResponseEntity<?> deleteEmployeeExtraQualification(long employeeExtraQualificationId);
}
