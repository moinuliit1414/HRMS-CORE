package com.synesis.hrmis.controller;

import com.synesis.hrmis.service.DesignationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DesignationController {
    private final DesignationService designationService;

    public DesignationController(DesignationService designationService) {
        this.designationService = designationService;
    }

    @GetMapping("/designation")
    public ResponseEntity<?> getAllDesignation() {
        return designationService.getAllDesignation();
    }

    @GetMapping("/designation/{id}")
    public ResponseEntity<?> getAllDesignationById(@PathVariable(name = "id") long designationId) {
        return designationService.getAllDesignationById(designationId);
    }

    @GetMapping("/designation-by-department/{id}")
    public ResponseEntity<?> getDesignationByDepartmentId(@PathVariable(name = "id") long departmentId) {
        return designationService.getAllDesignationByDepartmentId(departmentId);
    }

    @PostMapping("/designation")
    public ResponseEntity<?> saveDesignationInfo(@RequestBody Object designation) {
        return designationService.saveDesignationInfo(designation);
    }

    @PutMapping("/designation/{id}")
    public ResponseEntity<?> updateDesignationInfo(@PathVariable(name = "id") long designationId,@RequestBody Object designationInfo) {
        return designationService.updateDesignationInfo(designationId, designationInfo);
    }

    @DeleteMapping("/designation/{id}")
    public ResponseEntity<?> deleteDesignation(@PathVariable(name = "id") long designationId) {
        return designationService.deleteDesignation(designationId);
    }
}
