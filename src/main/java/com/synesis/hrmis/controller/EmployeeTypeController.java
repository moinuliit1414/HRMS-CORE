package com.synesis.hrmis.controller;

import com.synesis.hrmis.service.EmployeeTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeTypeController {

    private final EmployeeTypeService employeeTypeService;

    public EmployeeTypeController(EmployeeTypeService employeeTypeService) {
        this.employeeTypeService = employeeTypeService;
    }

    @GetMapping("/employee-type")
    public ResponseEntity<?> getAllEmployeeType() {
        return employeeTypeService.getAllEmployeeType();
    }

    @GetMapping("/employee-type/{id}")
    public ResponseEntity<?> getEmployeeTypeById(@PathVariable(name = "id") long employeeTypeId) {
        return employeeTypeService.getEmployeeTypeById(employeeTypeId);
    }

    @PostMapping("/employee-type")
    public ResponseEntity<?> saveEmployeeType(@RequestBody Object employeeTypeInfo) {
        return employeeTypeService.saveEmployeeType(employeeTypeInfo);
    }

    @PutMapping("/employee-type/{id}")
    public ResponseEntity<?> updateEmployeeType(@PathVariable(name = "id") long employeeTypeId,@RequestBody Object employeeTypeInfo) {
        return employeeTypeService.updateEmployeeType(employeeTypeId, employeeTypeInfo);
    }

    @DeleteMapping("/employee-type/{id}")
    public ResponseEntity<?> deleteEmployeeType(@PathVariable(name = "id") long employeeTypeId) {
        return employeeTypeService.deleteEmployeeType(employeeTypeId);
    }
}
