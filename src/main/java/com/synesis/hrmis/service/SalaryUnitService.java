package com.synesis.hrmis.service;

import org.springframework.http.ResponseEntity;

public interface SalaryUnitService {
    ResponseEntity<?> getAllSalaryUnit();

    ResponseEntity<?> getAllSalaryUnitId(long salaryUnitId);

    ResponseEntity<?> saveSalaryUnit(Object salaryUnit);

    ResponseEntity<?> updateSalaryUnit(long salaryUnitId, Object salaryUnitInfo);

    ResponseEntity<?> deleteSalaryUnit(long salaryUnitId);

}
