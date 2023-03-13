package com.synesis.hrmis.controller;

import com.synesis.hrmis.domain.FamilyInfo;
import com.synesis.hrmis.service.FamilyInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FamilyInfoController {

    private final FamilyInfoService familyInfoService;

    public FamilyInfoController(FamilyInfoService familyInfoService) {
        this.familyInfoService = familyInfoService;
    }

    @GetMapping("/family")
    public ResponseEntity<?> getAllFamilyInfo() {
        return familyInfoService.getAllFamilyInfo();
    }

    @GetMapping("/family/{id}")
    public ResponseEntity<?> getFamilyInfo(@PathVariable(name = "id") long employeeId) {
        return familyInfoService.getFamilyInfo(employeeId);
    }

    @PostMapping("/family")
    public ResponseEntity<?> saveFamilyInfo(@RequestBody FamilyInfo familyInfo) {
        return familyInfoService.saveFamilyInfo(familyInfo);
    }

    @PutMapping("/family")
    public ResponseEntity<?> updateFamilyInfo(@RequestBody FamilyInfo familyInfo) {
        return familyInfoService.updateFamilyInfo(familyInfo);
    }

    @DeleteMapping("/family/{id}")
    public ResponseEntity<?> deleteFamilyInfo(@PathVariable(name = "id") long id) {
        return familyInfoService.deleteFamilyInfo(id);
    }
}
