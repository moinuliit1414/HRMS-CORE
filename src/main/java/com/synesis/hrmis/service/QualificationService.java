package com.synesis.hrmis.service;

import com.synesis.hrmis.domain.EmployeeExtraQualification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface QualificationService {
    ResponseEntity<?> saveQualificationInfo(EmployeeExtraQualification qualificationInfo);
    ResponseEntity<?> getQualificationInfo(long employeeId);
    ResponseEntity<?> getAllQualificationInfo();
    ResponseEntity<?> updateQualificationInfo(EmployeeExtraQualification qualificationInfo);
    ResponseEntity<?> deleteQualificationInfo(long id);
}
