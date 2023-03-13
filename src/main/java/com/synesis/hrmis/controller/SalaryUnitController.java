package com.synesis.hrmis.controller;

import com.synesis.hrmis.service.SalaryUnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SalaryUnitController {

    private final SalaryUnitService salaryUnitService;

    public SalaryUnitController(SalaryUnitService salaryUnitService) {
        this.salaryUnitService = salaryUnitService;
    }

    @GetMapping("/salary-unit")
    public ResponseEntity<?> getAllSalaryUnit() {
        return salaryUnitService.getAllSalaryUnit();
    }

    @GetMapping("/salary-unit/{id}")
    public ResponseEntity<?> getAllSalaryUnitId(@PathVariable(name = "id") long salaryUnitId) {
        return salaryUnitService.getAllSalaryUnitId(salaryUnitId);
    }

    @PostMapping("/salary-unit")
    public ResponseEntity<?> saveSalaryUnit(@RequestBody Object salaryUnit) {
        return salaryUnitService.saveSalaryUnit(salaryUnit);
    }

    @PutMapping("/salary-unit/{id}")
    public ResponseEntity<?> updateSalaryUnit(@PathVariable(name = "id") long salaryUnitId, @RequestBody Object salaryUnitInfo) {
        return salaryUnitService.updateSalaryUnit(salaryUnitId, salaryUnitInfo);
    }

    @DeleteMapping("/salary-unit/{id}")
    public ResponseEntity<?> deleteSalaryUnit(@PathVariable(name = "id") long salaryUnitId) {
        return salaryUnitService.deleteSalaryUnit(salaryUnitId);
    }


}
