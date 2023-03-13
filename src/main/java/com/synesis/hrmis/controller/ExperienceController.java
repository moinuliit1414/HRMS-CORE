package com.synesis.hrmis.controller;

import com.synesis.hrmis.domain.Experience;
import com.synesis.hrmis.service.ExperienceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping("/experience")
    public ResponseEntity<?> getAllExperience() {
        return experienceService.getAllExperienceInfo();
    }

    @GetMapping("/experience/{id}")
    public ResponseEntity<?> getExperience(@PathVariable(name = "id") long employeeId) {
        return experienceService.getExperienceInfo(employeeId);
    }

    @PostMapping("/experience")
    public ResponseEntity<?> saveExperienceInfo(@RequestBody Experience experienceInfo) {
        return experienceService.saveExperienceInfo(experienceInfo);
    }

    @PutMapping("/experience")
    public ResponseEntity<?> updateExperience(@RequestBody Experience experienceInfo) {
        return experienceService.updateExperienceInfo(experienceInfo);
    }

    @DeleteMapping("/experience/{id}")
    public ResponseEntity<?> deleteExperience(@PathVariable(name = "id") long id) {
        return experienceService.deleteExperienceInfo(id);
    }
}
