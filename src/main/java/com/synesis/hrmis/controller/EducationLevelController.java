package com.synesis.hrmis.controller;

import com.synesis.hrmis.service.EducationLevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class EducationLevelController {
    private final EducationLevelService educationLevelService;

    public EducationLevelController(EducationLevelService educationLevelService) {
        this.educationLevelService = educationLevelService;
    }

    @GetMapping("/education-level")
    public ResponseEntity<?> getAllEducationLevel() {
        return educationLevelService.getAllEducationLevel();
    }

    @GetMapping("/education-level/{id}")
    public ResponseEntity<?> getAllEducationLevelById(@PathVariable(name = "id") long educationLevelId) {
        return educationLevelService.getAllEducationLevelById(educationLevelId);
    }

    @PostMapping("/education-level")
    public ResponseEntity<?> saveEducationLevelInfo(@RequestBody Object educationLevel) {
        return educationLevelService.saveEducationLevelInfo(educationLevel);
    }

    @PutMapping("/education-level/{id}")
    public ResponseEntity<?> updateDesignationInfo(@PathVariable(name = "id") long educationLevelId, @RequestBody Object educationLevelInfo) {
        return educationLevelService.updateEducationLevel(educationLevelId, educationLevelInfo);
    }

    @DeleteMapping("/education-level/{id}")
    public ResponseEntity<?> deleteDesignation(@PathVariable(name = "id") long educationLevelId) {
        return educationLevelService.deleteEducationLevel(educationLevelId);
    }

}
