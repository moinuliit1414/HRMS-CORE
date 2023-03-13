package com.synesis.hrmis.service.impl;


import com.synesis.hrmis.domain.EmployeeType;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.EmployeeTypeRepository;
import com.synesis.hrmis.service.EmployeeTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeTypeServiceImpl implements EmployeeTypeService {

    private final EmployeeTypeRepository employeeTypeRepository;

    ModelMapper mapper = new ModelMapper();

    public EmployeeTypeServiceImpl(EmployeeTypeRepository employeeTypeRepository) {
        this.employeeTypeRepository = employeeTypeRepository;
    }

    @Override
    public ResponseEntity<?> getAllEmployeeType() {
        List<EmployeeType> employeeType = employeeTypeRepository.findAll();
        return new ResponseEntity(new SuccessResponse(employeeType), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getEmployeeTypeById(long employeeTypeId) {
        Optional<EmployeeType> employeeType = employeeTypeRepository.findById(employeeTypeId);
        if (employeeType.isPresent()) {
            return new ResponseEntity(new SuccessResponse(employeeType), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Failed to get Data"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> saveEmployeeType(Object employeeTypeInfo) {
        EmployeeType employeeType = mapper.map(employeeTypeInfo, EmployeeType.class);
        try {
            employeeTypeRepository.save(employeeType);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to Save Data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new SuccessResponse("Successfully saved EmployeeType Data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateEmployeeType(long employeeTypeId, Object employeeTypeNewData) {
        Optional<EmployeeType> employeeTypeExistData = employeeTypeRepository.findById(employeeTypeId);
        if (employeeTypeExistData.isPresent()) {
            mapper.map(employeeTypeNewData, employeeTypeExistData.get());
            return new ResponseEntity<>(employeeTypeRepository.save(employeeTypeExistData.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Failed to Update Data"), HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity<?> deleteEmployeeType(long employeeTypeId) {
        Optional<EmployeeType> employeeType = employeeTypeRepository.findById(employeeTypeId);
        if (employeeType.isPresent()) {
            employeeTypeRepository.delete(employeeType.get());
            return new ResponseEntity(new SuccessResponse("Successfully Deleted EmployeeType data"), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("EmployeeType Data is not Exists."), HttpStatus.NOT_FOUND);
    }
}
