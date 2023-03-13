package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.enums.*;
import com.synesis.hrmis.service.BasicInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BasicInformationServiceImpl implements BasicInformationService {
    @Override
    public ResponseEntity<?> getAllBasicInfo() {
        Map basicInfo = new HashMap<>();
        basicInfo.put("religions", Religion.values());
        basicInfo.put("genders", Gender.values());
        basicInfo.put("bloodTypes", getAllBloodType());
        basicInfo.put("maritalStatus", MaritalStatus.values());
        basicInfo.put("employmentTypes", getAllEmploymentType());
        return new ResponseEntity<>(new SuccessResponse<>(basicInfo), HttpStatus.OK);
    }

    public List<String> getAllBloodType() {
        List<String> bloodGroup = new ArrayList<>();
        Arrays.asList(BloodGroup.values())
                .forEach(bloodType -> bloodGroup.add(bloodType.toString()));
        return bloodGroup;
    }

    public List<String> getAllEmploymentType() {
        List<String> employmentTypes = new ArrayList<>();
        Arrays.asList(EmploymentType.values())
                .forEach(employmentType -> employmentTypes.add(employmentType.toString()));
        return employmentTypes;
    }
}
