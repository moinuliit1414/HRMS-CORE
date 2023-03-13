package com.synesis.hrmis.service;

import org.springframework.http.ResponseEntity;

public interface DesignationService {
    ResponseEntity<?> getAllDesignation();

    ResponseEntity<?> getAllDesignationById(long designationId);
    ResponseEntity<?> getDesignationByDepartmentId(long departmentId);


    ResponseEntity<?> saveDesignationInfo(Object designation);

    ResponseEntity<?> updateDesignationInfo(long designationId, Object designation);

    ResponseEntity<?> deleteDesignation(long designationId);

    ResponseEntity<?> getAllDesignationByDepartmentId(long departmentId);
}
