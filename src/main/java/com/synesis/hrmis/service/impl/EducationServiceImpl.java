package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.Education;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.EducationRepository;
import com.synesis.hrmis.service.EducationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;

    ModelMapper mapper = new ModelMapper();

    public EducationServiceImpl(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @Override
    public ResponseEntity<?> saveEducationInfo(Education educationInfo) {
        try {
            educationRepository.save(educationInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse<>("Failed to save education data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new SuccessResponse<>("Successfully saved education data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getEducationInfo(long employeeId) {
        List<Education> education = educationRepository.findEducationsByEmployeeId(employeeId);
        if(!education.isEmpty()) {
            return new ResponseEntity<>(new SuccessResponse<>(education), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse<>("Education Info not found"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllEducationInfo() {
        List<Education> educationList = educationRepository.findAll();
        return new ResponseEntity<>(new SuccessResponse<>(educationList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateEducationInfo(Education educationInfo) {
        Optional<Education> existingEducation = educationRepository
                .findEducationsByIdAndEmployeeId(educationInfo.getId(), educationInfo.getEmployeeId());
        if(existingEducation.isPresent()) {
            try {
                mapper.map(educationInfo, existingEducation.get());
                educationRepository.save(existingEducation.get());
            } catch (Exception ex) {
                ex.printStackTrace();
                return new ResponseEntity<>(new ErrorResponse<>("Failed to update education data"),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(new SuccessResponse<>("Successfully updated education data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteEducationInfo(long id) {
        try {
            educationRepository.deleteById(id);
            return new ResponseEntity<>(new SuccessResponse<>("Successfully deleted education data"), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse<>("Failed to delete education data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
