package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.Address;
import com.synesis.hrmis.domain.EmployeeExtraQualification;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.QualificationRepository;
import com.synesis.hrmis.service.QualificationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QualificationServiceImpl implements QualificationService {

    private final QualificationRepository qualificationRepository;

    ModelMapper mapper = new ModelMapper();

    public QualificationServiceImpl(QualificationRepository qualificationRepository) {
        this.qualificationRepository = qualificationRepository;
    }

    @Override
    public ResponseEntity<?> saveQualificationInfo(EmployeeExtraQualification qualificationInfo) {
        try {
            qualificationRepository.save(qualificationInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse<>("Failed to save qualification data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new SuccessResponse<>("Successfully saved qualification data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getQualificationInfo(long employeeId) {
        List<EmployeeExtraQualification> qualifications = qualificationRepository.findEmployeeExtraQualificationByEmployeeId(employeeId);
        if(!qualifications.isEmpty()) {
            return new ResponseEntity<>(new SuccessResponse<>(qualifications), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse<>("Qualification Info not found"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllQualificationInfo() {
        List<EmployeeExtraQualification> qualificationList = qualificationRepository.findAll();
        return new ResponseEntity<>(new SuccessResponse<>(qualificationList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateQualificationInfo(EmployeeExtraQualification qualificationInfo) {
        Optional<EmployeeExtraQualification> existingQualification = qualificationRepository
                .findEmployeeExtraQualificationByIdAndEmployeeId(qualificationInfo.getId(), qualificationInfo.getEmployeeId());
        if(existingQualification.isPresent()) {
            try {
                mapper.map(qualificationInfo, existingQualification.get());
                qualificationRepository.save(existingQualification.get());
            } catch (Exception ex) {
                ex.printStackTrace();
                return new ResponseEntity<>(new ErrorResponse<>("Failed to update qualification data"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(new SuccessResponse<>("Successfully updated qualification data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteQualificationInfo(long id) {
        try {
            qualificationRepository.deleteById(id);
            return new ResponseEntity<>(new SuccessResponse<>("Successfully deleted qualification data"), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse<>("Failed to delete qualification data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
