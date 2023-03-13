package com.synesis.hrmis.service;

import org.springframework.http.ResponseEntity;

public interface EducationLevelService {
    ResponseEntity<?> getAllEducationLevel();

    ResponseEntity<?> getAllEducationLevelById(long educationLevelId);

    ResponseEntity<?> saveEducationLevelInfo(Object educationLevel);

    ResponseEntity<?> updateEducationLevel(long educationLevelId, Object educationLevelInfo);

    ResponseEntity<?> deleteEducationLevel(long educationLevelId);
}
