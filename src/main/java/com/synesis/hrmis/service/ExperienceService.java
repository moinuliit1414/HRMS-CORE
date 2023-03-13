package com.synesis.hrmis.service;

import com.synesis.hrmis.domain.Experience;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ExperienceService {
    ResponseEntity<?> saveExperienceInfo(Experience experienceInfo);
    ResponseEntity<?> getExperienceInfo(long employeeId);
    ResponseEntity<?> getAllExperienceInfo();
    ResponseEntity<?> updateExperienceInfo(Experience experienceInfo);
    ResponseEntity<?> deleteExperienceInfo(long id);
}
