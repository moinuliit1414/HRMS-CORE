package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.Experience;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.ExperienceRepository;
import com.synesis.hrmis.service.ExperienceService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;

    ModelMapper mapper = new ModelMapper();

    public ExperienceServiceImpl(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    public ResponseEntity<?> saveExperienceInfo(Experience experienceInfo) {
        try {
            experienceRepository.save(experienceInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse<>("Failed to save experience data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new SuccessResponse<>("Successfully saved experience data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getExperienceInfo(long employeeId) {
        List<Experience> experience = experienceRepository.findExperienceByEmployeeId(employeeId);
        if(!experience.isEmpty()) {
            return new ResponseEntity<>(new SuccessResponse<>(experience), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse<>("Experience Info not found"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllExperienceInfo() {
        List<Experience> experienceList = experienceRepository.findAll();
        return new ResponseEntity<>(new SuccessResponse<>(experienceList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateExperienceInfo(Experience experienceInfo) {
        Optional<Experience> existingExperience = experienceRepository
                .findExperienceByIdAndEmployeeId(experienceInfo.getId(), experienceInfo.getEmployeeId());
        if(existingExperience.isPresent()) {
            try {
                mapper.map(experienceInfo, existingExperience.get());
                experienceRepository.save(existingExperience.get());
            } catch (Exception ex) {
                ex.printStackTrace();
                return new ResponseEntity<>(new ErrorResponse<>("Failed to update experience data"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(new SuccessResponse<>("Successfully updated experience data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteExperienceInfo(long id) {
        try {
            experienceRepository.deleteById(id);
            return new ResponseEntity<>(new SuccessResponse<>("Successfully deleted experience data"), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse<>("Failed to delete experience data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
