package com.synesis.hrmis.service;

import com.synesis.hrmis.domain.HRInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface HRInfoService {
    ResponseEntity<?> saveHRInfo(HRInfo hrInfo);
    ResponseEntity<?> getHRInfo(long employeeId);
    ResponseEntity<?> getAllHRInfo();
    ResponseEntity<?> updateHRInfo(HRInfo hrInfo);
    ResponseEntity<?> deleteHRInfo(long id);
}
