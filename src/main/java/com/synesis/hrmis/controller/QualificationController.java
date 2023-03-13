package com.synesis.hrmis.controller;

import com.synesis.hrmis.domain.EmployeeExtraQualification;
import com.synesis.hrmis.service.QualificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QualificationController {

    private final QualificationService qualificationService;

    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    @GetMapping("/qualification")
    public ResponseEntity<?> getAllQualification() {
        return qualificationService.getAllQualificationInfo();
    }

    @GetMapping("/qualification/{id}")
    public ResponseEntity<?> getQualification(@PathVariable(name = "id") long employeeId) {
        return qualificationService.getQualificationInfo(employeeId);
    }

    @PostMapping("/qualification")
    public ResponseEntity<?> saveQualificationInfo(@RequestBody EmployeeExtraQualification qualificationInfo) {
        return qualificationService.saveQualificationInfo(qualificationInfo);
    }

    @PutMapping("/qualification")
    public ResponseEntity<?> updateQualification(@RequestBody EmployeeExtraQualification qualificationInfo) {
        return qualificationService.updateQualificationInfo(qualificationInfo);
    }

    @DeleteMapping("/qualification/{id}")
    public ResponseEntity<?> deleteQualification(@PathVariable(name = "id") long id) {
        return qualificationService.deleteQualificationInfo(id);
    }
}
