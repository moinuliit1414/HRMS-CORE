package com.synesis.hrmis.service;

import com.synesis.hrmis.domain.FamilyInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface FamilyInfoService {
    ResponseEntity<?> saveFamilyInfo(FamilyInfo familyInfo);
    ResponseEntity<?> getFamilyInfo(long employeeId);
    ResponseEntity<?> getAllFamilyInfo();
    ResponseEntity<?> updateFamilyInfo(FamilyInfo familyInfo);
    ResponseEntity<?> deleteFamilyInfo(long id);
}
