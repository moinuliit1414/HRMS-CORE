package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.EducationLevel;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.EducationLevelRepository;
import com.synesis.hrmis.service.EducationLevelService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationLevelServiceImpl implements EducationLevelService {
    private final EducationLevelRepository educationLevelTypeRepository;

    ModelMapper mapper = new ModelMapper();

    public EducationLevelServiceImpl(EducationLevelRepository educationLevelRepository) {
        this.educationLevelTypeRepository = educationLevelRepository;
    }

    @Override
    public ResponseEntity<?> getAllEducationLevel() {
        List<EducationLevel> educationLevelList = educationLevelTypeRepository.findAll();
        return new ResponseEntity<>(new SuccessResponse<>(educationLevelList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllEducationLevelById(long educationLevelId) {
        Optional<EducationLevel> educationLevel = educationLevelTypeRepository.findById(educationLevelId);
        if (educationLevel.isPresent()) {
            return new ResponseEntity<>(new SuccessResponse<>(educationLevel), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse<>("Failed to Get Education Level Data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveEducationLevelInfo(Object educationLevelInfo) {
        EducationLevel educationLevel = mapper.map(educationLevelInfo, EducationLevel.class);
        try {
            educationLevelTypeRepository.save(educationLevel);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse<>("Failed to Save Education Level Data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new SuccessResponse<>("Successfully Saved Education Level Data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateEducationLevel(long educationLevelId, Object educationLevelNewData) {
        Optional<EducationLevel> educationLevelExistData = educationLevelTypeRepository.findById(educationLevelId);
        if (educationLevelExistData.isPresent()) {
            mapper.map(educationLevelNewData, educationLevelExistData.get());
            return new ResponseEntity<>(educationLevelTypeRepository.save(educationLevelExistData.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse<>("Internal Problem to Updated EducationLevel Data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<?> deleteEducationLevel(long educationLevelId) {
        Optional<EducationLevel> educationLevel = educationLevelTypeRepository.findById(educationLevelId);
        if (educationLevel.isPresent()) {
            educationLevelTypeRepository.delete(educationLevel.get());
            return new ResponseEntity<>(new SuccessResponse<>("Successfully deleted Education Level data"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse<>("Education Level does not exist"), HttpStatus.NOT_FOUND);
        }
    }
}
