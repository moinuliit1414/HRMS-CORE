package com.synesis.hrmis.controller;

import com.synesis.hrmis.domain.Education;
import com.synesis.hrmis.service.EducationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/education")
    public ResponseEntity<?> getAllEducationInfo() {
        return educationService.getAllEducationInfo();
    }

    @GetMapping("/education/{id}")
    public ResponseEntity<?> getEducationInfo(@PathVariable(name = "id") long employeeId) {
        return educationService.getEducationInfo(employeeId);
    }

    @PostMapping("/education")
    public ResponseEntity<?> saveEducationInfo(@RequestBody Education educationInfo) {
        return educationService.saveEducationInfo(educationInfo);
    }

    @PutMapping("/education")
    public ResponseEntity<?> updateEducation(@RequestBody Education educationInfo) {
        return educationService.updateEducationInfo(educationInfo);
    }

    @DeleteMapping("/education/{id}")
    public ResponseEntity<?> deleteEducation(@PathVariable(name = "id") long id) {
        return educationService.deleteEducationInfo(id);
    }
}
