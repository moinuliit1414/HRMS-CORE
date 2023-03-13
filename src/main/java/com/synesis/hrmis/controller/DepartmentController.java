package com.synesis.hrmis.controller;

import com.synesis.hrmis.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department")
    public ResponseEntity<?> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable(name = "id") long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping("/department")
    public ResponseEntity<?> saveDepartmentInfo(@RequestBody Object department) {
        return departmentService.saveDepartmentInfo(department);
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<?> updateDepartmentInfo(@PathVariable("id")long departmentId,@RequestBody Object departmentInfo) {
        return departmentService.updateDepartmentInfo(departmentId, departmentInfo);
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable(name = "id") long departmentId) {
        return departmentService.deleteDepartment(departmentId);
    }

}
