package com.synesis.hrmis.controller;

import com.synesis.hrmis.service.EmployeeExtraQualificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeExtraQualificationController {
    private final EmployeeExtraQualificationService employeeExtraQualificationService;

    public EmployeeExtraQualificationController(EmployeeExtraQualificationService employeeExtraQualificationService) {
        this.employeeExtraQualificationService = employeeExtraQualificationService;
    }

    @GetMapping("/employee-extra-qualification")
    public ResponseEntity<?> getAllEmployeeExtraQualification() {
        return employeeExtraQualificationService.getAllEmployeeExtraQualification();
    }

    @GetMapping("/employee-extra-qualification/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable(name = "id") long employeeExtraQualificationId) {
        return employeeExtraQualificationService.getAllEmployeeExtraQualificationById(employeeExtraQualificationId);
    }

    @PostMapping("/employee-extra-qualification")
    public ResponseEntity<?> saveEmployeeInfo(@RequestBody Object employeeExtraQualificationInfo) {
        return employeeExtraQualificationService.saveEmployeeExtraQualification(employeeExtraQualificationInfo);
    }

    @PutMapping("/employee-extra-qualification/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") long employeeExtraQualificationId, @RequestBody Object employeeExtraQualificationInfo) {
        return employeeExtraQualificationService.updateEmployeeExtraQualification(employeeExtraQualificationId, employeeExtraQualificationInfo);
    }

    @DeleteMapping("/employee-extra-qualification/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(name = "id") long employeeExtraQualificationId) {
        return employeeExtraQualificationService.deleteEmployeeExtraQualification(employeeExtraQualificationId);
    }
}
