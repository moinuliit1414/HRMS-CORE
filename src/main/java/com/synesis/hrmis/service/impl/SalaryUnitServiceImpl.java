package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.Designation;
import com.synesis.hrmis.domain.SalaryUnit;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.SalaryUnitRepository;
import com.synesis.hrmis.service.SalaryUnitService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryUnitServiceImpl implements SalaryUnitService {

    private final SalaryUnitRepository salaryUnitRepository;

    ModelMapper mapper = new ModelMapper();

    public SalaryUnitServiceImpl(SalaryUnitRepository salaryUnitRepository) {
        this.salaryUnitRepository = salaryUnitRepository;
    }

    @Override
    public ResponseEntity<?> getAllSalaryUnit() {
        List<SalaryUnit> salaryUnitList = salaryUnitRepository.findAll();
        return new ResponseEntity(new SuccessResponse(salaryUnitList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllSalaryUnitId(long salaryUnitId) {
        Optional<SalaryUnit> salaryUnit = salaryUnitRepository.findById(salaryUnitId);
        if (salaryUnit.isPresent()) {
            return new ResponseEntity(new SuccessResponse(salaryUnit), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Failed to get Salary Unit data"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> saveSalaryUnit(Object salaryUnitInfo) {
        SalaryUnit salaryUnit = mapper.map(salaryUnitInfo, SalaryUnit.class);
        try {
            salaryUnitRepository.save(salaryUnit);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to save Salary Unit data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new SuccessResponse("Successfully saved Salary Unit data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateSalaryUnit(long salaryUnitID, Object salaryUnitNewData) {
        Optional<SalaryUnit> salaryUnitExistData = salaryUnitRepository.findById(salaryUnitID);
        if (salaryUnitExistData.isPresent()) {
            mapper.map(salaryUnitNewData, salaryUnitExistData.get());
            return new ResponseEntity<>(salaryUnitRepository.save(salaryUnitExistData.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> deleteSalaryUnit(long salaryUnitId) {
        Optional<SalaryUnit> salaryUnit = salaryUnitRepository.findById(salaryUnitId);
        if (salaryUnit.isPresent()) {
            salaryUnitRepository.delete(salaryUnit.get());
            return new ResponseEntity(new SuccessResponse("Successfully deleted Salary Unit data"), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Salary Unit data does not exist"), HttpStatus.NOT_FOUND);
    }
}
