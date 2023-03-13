package com.synesis.hrmis.service;

import com.synesis.hrmis.domain.Education;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface EducationService {
    ResponseEntity<?> saveEducationInfo(Education educationInfo);
    ResponseEntity<?> getEducationInfo(long employeeId);
    ResponseEntity<?> getAllEducationInfo();
    ResponseEntity<?> updateEducationInfo(Education educationInfo);
    ResponseEntity<?> deleteEducationInfo(long id);
}
