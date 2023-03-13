package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.EmployeeExtraQualification;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.QualificationRepository;
import com.synesis.hrmis.service.EmployeeExtraQualificationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeExtraQualificationServiceImpl implements EmployeeExtraQualificationService {

    private final QualificationRepository employeeExtraQualificationRepository;

    ModelMapper mapper = new ModelMapper();

    public EmployeeExtraQualificationServiceImpl(QualificationRepository employeeExtraQualificationRepository) {
        this.employeeExtraQualificationRepository = employeeExtraQualificationRepository;
    }

    @Override
    public ResponseEntity<?> getAllEmployeeExtraQualification() {
        List<EmployeeExtraQualification> employeeExtraQualifications = employeeExtraQualificationRepository.findAll();
        if (employeeExtraQualifications.isEmpty()) {
            return new ResponseEntity(new ErrorResponse("Data doesn't exist"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(new SuccessResponse(employeeExtraQualifications), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllEmployeeExtraQualificationById(long employeeExtraQualificationId) {
        Optional<EmployeeExtraQualification> employeeExtraQualificationInfo = employeeExtraQualificationRepository.findById(employeeExtraQualificationId);
        if (employeeExtraQualificationInfo.isPresent()) {
            return new ResponseEntity(new SuccessResponse(employeeExtraQualificationInfo), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Failed to get Employee Extra Qualification Info Data"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> saveEmployeeExtraQualification(Object employeeExtraQualificationInfo) {
        EmployeeExtraQualification employeeExtraQualification = mapper.map(employeeExtraQualificationInfo, EmployeeExtraQualification.class);
        try {
            employeeExtraQualificationRepository.save(employeeExtraQualification);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse<>("Failed to Saved Employee Extra Qualification data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new SuccessResponse<>(employeeExtraQualification), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateEmployeeExtraQualification(long employeeExtraQualificationId, Object employeeExtraQualificationNewData) {
        Optional<EmployeeExtraQualification> employeeExtraQualificationExistData = employeeExtraQualificationRepository.findById(employeeExtraQualificationId);
        if (employeeExtraQualificationExistData.isPresent()) {
            mapper.map(employeeExtraQualificationNewData, employeeExtraQualificationExistData.get());
            return new ResponseEntity<>(employeeExtraQualificationRepository.save(employeeExtraQualificationExistData.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse<>("Failed to Update Employee Extra Qualification Data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<?> deleteEmployeeExtraQualification(long employeeExtraQualificationId) {
        Optional<EmployeeExtraQualification> employeeExtraQualification = employeeExtraQualificationRepository.findById(employeeExtraQualificationId);
        if (employeeExtraQualification.isPresent()) {
            employeeExtraQualificationRepository.delete(employeeExtraQualification.get());
            return new ResponseEntity<>(new SuccessResponse<>("Successfully  Deleted Employee Extra Qualification Data"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse<>("Employee Extra Data Doesn't Exist"), HttpStatus.NOT_FOUND);
    }
}
