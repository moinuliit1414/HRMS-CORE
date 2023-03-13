package com.synesis.hrmis.service;

import org.springframework.http.ResponseEntity;

public interface SalaryConfigService {
    ResponseEntity<?> getAllSalaryConfig();

    ResponseEntity<?> getAllSalaryConfigById(long salaryConfigId);

    ResponseEntity<?> saveSalaryConfigInfo(Object salaryConfig);

    ResponseEntity<?> updateSalaryConfigInfo(long salaryConfigId, Object salaryConfigInfo);

    ResponseEntity<?> deleteSalaryConfig(long salaryConfigId);
}
