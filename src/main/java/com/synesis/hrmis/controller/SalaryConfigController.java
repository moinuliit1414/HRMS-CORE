package com.synesis.hrmis.controller;

import com.synesis.hrmis.service.SalaryConfigService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SalaryConfigController {
    private final SalaryConfigService salaryConfigService;

    public SalaryConfigController(SalaryConfigService salaryConfigService) {
        this.salaryConfigService = salaryConfigService;
    }

    @GetMapping("/salary-config")
    public ResponseEntity<?> getAllSalaryConfig() {
        return salaryConfigService.getAllSalaryConfig();
    }

    @GetMapping("/salary-config/{id}")
    public ResponseEntity<?> getAllSalaryConfigById(@PathVariable(name = "id") long salaryConfigId) {
        return salaryConfigService.getAllSalaryConfigById(salaryConfigId);
    }

    @PostMapping("/salary-config")
    public ResponseEntity<?> saveSalaryConfigInfo(@RequestBody Object salaryConfig) {
        return salaryConfigService.saveSalaryConfigInfo(salaryConfig);
    }

    @PutMapping("/salary-config/{id}")
    public ResponseEntity<?> updateSalaryConfigInfo(@PathVariable(name = "id") long salaryConfigId, @RequestBody Object salaryConfigInfo) {
        return salaryConfigService.updateSalaryConfigInfo(salaryConfigId, salaryConfigInfo);
    }

    @DeleteMapping("/salary-config/{id}")
    public ResponseEntity<?> deleteSalaryConfig(@PathVariable(name = "id") long salaryConfigId) {
        return salaryConfigService.deleteSalaryConfig(salaryConfigId);
    }
}
