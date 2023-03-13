package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.Grade;
import com.synesis.hrmis.domain.ResultType;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.ResultTypeRepository;
import com.synesis.hrmis.service.ResultTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultTypeServiceImpl implements ResultTypeService {
    private final ResultTypeRepository resultTypeRepository;
    ModelMapper mapper = new ModelMapper();

    public ResultTypeServiceImpl(ResultTypeRepository resultTypeRepository) {
        this.resultTypeRepository = resultTypeRepository;
    }

    @Override
    public ResponseEntity<?> getAllResultType() {
        List<ResultType> resultTypeList = resultTypeRepository.findAll();
        return new ResponseEntity<>(new SuccessResponse<>(resultTypeList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getResultTypeById(long resultTypeID) {
        Optional<ResultType> resultType = resultTypeRepository.findById(resultTypeID);
        if (resultType.isPresent()) {
            return new ResponseEntity(new SuccessResponse(resultType), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Failed to get Result Type Data"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> saveResultType(Object resultTypeInfo) {
        ResultType resultType = mapper.map(resultTypeInfo, ResultType.class);
        try {
            resultTypeRepository.save(resultType);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to Result Type Data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new SuccessResponse("Successfully saved Result Type Data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateResultType(long resultTypeId, Object resultTypeNewData) {
        Optional<ResultType> resultTypeInfoExistData = resultTypeRepository.findById(resultTypeId);
        if (resultTypeInfoExistData.isPresent()) {
            mapper.map(resultTypeNewData, resultTypeInfoExistData.get());
            return new ResponseEntity<>(resultTypeRepository.save(resultTypeInfoExistData.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Internal Problem to Updated Result Type Data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<?> deleteResultTypeById(long resultTypeID) {
        Optional<ResultType> resultType = resultTypeRepository.findById(resultTypeID);
        if (resultType.isPresent()) {
            resultTypeRepository.delete(resultType.get());
            return new ResponseEntity(new SuccessResponse("Successfully deleted Result Type data"), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Result Type Data does not exist"), HttpStatus.NOT_FOUND);

    }
}
