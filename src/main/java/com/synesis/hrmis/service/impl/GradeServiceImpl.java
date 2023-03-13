package com.synesis.hrmis.service.impl;


import com.synesis.hrmis.domain.Department;
import com.synesis.hrmis.domain.Grade;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.GradeRepository;
import com.synesis.hrmis.service.GradeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    ModelMapper mapper = new ModelMapper();

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public ResponseEntity<?> getAllGrade() {
        List<Grade> gradeList = gradeRepository.findAll();
        return new ResponseEntity<>(new SuccessResponse<>(gradeList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllGradeById(long gradeById) {
        Optional<Grade> grade = gradeRepository.findById(gradeById);
        if (grade.isPresent()) {
            return new ResponseEntity(new SuccessResponse(grade), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Failed to get Employee Data"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> saveGradeInfo(Object gradeInfo) {
        Grade grade = mapper.map(gradeInfo, Grade.class);
        try {
            gradeRepository.save(grade);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to save Grade Data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new SuccessResponse("Successfully saved Grade Data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateGradeInfo(long gradeId, Object gradeInfoNewData) {
        Optional<Grade> gradeInfoExistData = gradeRepository.findById(gradeId);
        if (gradeInfoExistData.isPresent()) {
            mapper.map(gradeInfoNewData, gradeInfoExistData.get());
            return new ResponseEntity<>(gradeRepository.save(gradeInfoExistData.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Internal Problem to Updated Grade Data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<?> deleteGrade(long gradeId) {
        Optional<Grade> grade = gradeRepository.findById(gradeId);
        if (grade.isPresent()) {
            gradeRepository.delete(grade.get());
            return new ResponseEntity(new SuccessResponse("Successfully deleted Grade data"), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Grade Data does not exist"), HttpStatus.NOT_FOUND);

    }
}
