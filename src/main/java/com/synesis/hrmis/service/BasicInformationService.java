package com.synesis.hrmis.service;

import com.synesis.hrmis.domain.Education;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BasicInformationService {
    ResponseEntity<?> getAllBasicInfo();
}
