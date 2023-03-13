package com.synesis.hrmis.service;

import org.springframework.http.ResponseEntity;

public interface DepartmentService {
    ResponseEntity<?> getAllDepartment();

    ResponseEntity<?> getDepartmentById(long departmentId);

    ResponseEntity<?> saveDepartmentInfo(Object department);

    ResponseEntity<?> updateDepartmentInfo(long departmentId, Object departmentInfo);

    ResponseEntity<?> deleteDepartment(long departmentId);
}
