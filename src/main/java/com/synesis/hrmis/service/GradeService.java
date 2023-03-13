package com.synesis.hrmis.service;

import org.springframework.http.ResponseEntity;

public interface GradeService {
    ResponseEntity<?> getAllGrade();

    ResponseEntity<?> getAllGradeById(long gradeById);

    ResponseEntity<?> saveGradeInfo(Object grade);

    ResponseEntity<?> updateGradeInfo(long gradeId, Object gradeInfo);

    ResponseEntity<?> deleteGrade(long gradeId);
}
