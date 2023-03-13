package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.SalaryConfig;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.SalaryConfigRepository;
import com.synesis.hrmis.service.SalaryConfigService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class SalaryConfigServiceImpl implements SalaryConfigService {

    private final SalaryConfigRepository salaryConfigRepository;

    ModelMapper mapper = new ModelMapper();

    public SalaryConfigServiceImpl(SalaryConfigRepository salaryConfigRepository) {
        this.salaryConfigRepository = salaryConfigRepository;
    }

    @Override
    public ResponseEntity<?> getAllSalaryConfig() {
        List<SalaryConfig> salaryConfigst = salaryConfigRepository.findAll();
        return new ResponseEntity(new SuccessResponse(salaryConfigst), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllSalaryConfigById(long salaryConfigId) {
        Optional<SalaryConfig> salaryConfig = salaryConfigRepository.findById(salaryConfigId);
        if (salaryConfig.isPresent()) {
            return new ResponseEntity(new SuccessResponse(salaryConfig), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Failed to get Salary Config data"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> saveSalaryConfigInfo(Object salaryConfigInfo) {
        SalaryConfig salaryConfig = mapper.map(salaryConfigInfo, SalaryConfig.class);
        try {
            salaryConfigRepository.save(salaryConfig);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to save Salary Config data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new SuccessResponse("Successfully saved Salary Config data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateSalaryConfigInfo(long salaryConfigId,Object salaryConfigNewData) {
        Optional<SalaryConfig> salaryConfigExistData = salaryConfigRepository.findById(salaryConfigId);
        if (salaryConfigExistData.isPresent()) {
            mapper.map(salaryConfigNewData, salaryConfigExistData.get());
            return new ResponseEntity<>(salaryConfigRepository.save(salaryConfigExistData.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> deleteSalaryConfig(long salaryConfigId) {
        Optional<SalaryConfig> salaryConfig = salaryConfigRepository.findById(salaryConfigId);
        if (salaryConfig.isPresent()) {
            salaryConfigRepository.delete(salaryConfig.get());
            return new ResponseEntity(new SuccessResponse("Successfully deleted Salary Config data"), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Salary Config data does not exist"), HttpStatus.NOT_FOUND);
    }
}
