package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.FamilyInfo;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.FamilyInfoRepository;
import com.synesis.hrmis.service.FamilyInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyInfoServiceImpl implements FamilyInfoService {

    private final FamilyInfoRepository familyInfoRepository;

    ModelMapper mapper = new ModelMapper();


    public FamilyInfoServiceImpl(FamilyInfoRepository familyInfoRepository) {
        this.familyInfoRepository = familyInfoRepository;
    }

    @Override
    public ResponseEntity<?> saveFamilyInfo(FamilyInfo familyInfo) {
        try {
            familyInfoRepository.save(familyInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse<>("Failed to save family info"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new SuccessResponse<>("Successfully saved family info"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getFamilyInfo(long employeeId) {
        List<FamilyInfo> families = familyInfoRepository.findFamilyInfoByEmployeeId(employeeId);
        if(!families.isEmpty()) {
            return new ResponseEntity<>(new SuccessResponse<>(families), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse<>("Family Info not found"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllFamilyInfo() {
        List<FamilyInfo> familyInfoList = familyInfoRepository.findAll();
        return new ResponseEntity<>(new SuccessResponse<>(familyInfoList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateFamilyInfo(FamilyInfo familyInfo) {
        Optional<FamilyInfo> existingFamilyInfo = familyInfoRepository
                .findFamilyInfoByIdAndEmployeeId(familyInfo.getId(), familyInfo.getEmployeeId());
        if(existingFamilyInfo.isPresent()) {
            try {
                mapper.map(familyInfo, existingFamilyInfo.get());
                familyInfoRepository.save(existingFamilyInfo.get());
            } catch (Exception ex) {
                ex.printStackTrace();
                return new ResponseEntity<>(new ErrorResponse<>("Failed to update family info"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(new SuccessResponse<>("Successfully updated family info"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteFamilyInfo(long id) {
        try {
            familyInfoRepository.deleteById(id);
            return new ResponseEntity<>(new SuccessResponse<>("Successfully deleted family info"), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse<>("Failed to delete family info"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
