package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.HRInfo;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.HRInfoRepository;
import com.synesis.hrmis.service.HRInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HRInfoServiceImpl implements HRInfoService {

    private final HRInfoRepository hrInfoRepository;

    ModelMapper mapper = new ModelMapper();

    public HRInfoServiceImpl(HRInfoRepository hrInfoRepository) {
        this.hrInfoRepository = hrInfoRepository;
    }

    @Override
    public ResponseEntity<?> saveHRInfo(HRInfo hrInfo) {
        try {
            hrInfoRepository.save(hrInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse<>("Failed to save HR info"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new SuccessResponse<>("Successfully saved HR info"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getHRInfo(long employeeId) {
        List<HRInfo> hrInfos = hrInfoRepository.findHRInfoByEmployeeId(employeeId);
        if(!hrInfos.isEmpty()) {
            return new ResponseEntity<>(new SuccessResponse<>(hrInfos), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse<>("HR Info not found"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllHRInfo() {
        List<HRInfo> hrInfoList = hrInfoRepository.findAll();
        return new ResponseEntity<>(new SuccessResponse<>(hrInfoList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateHRInfo(HRInfo hrInfo) {
        Optional<HRInfo> existingHRInfo = hrInfoRepository.findHRInfoByIdAndEmployeeId(hrInfo.getId(), hrInfo.getEmployeeId());
        if(existingHRInfo.isPresent()) {
            try {
                mapper.map(hrInfo, existingHRInfo.get());
                hrInfoRepository.save(existingHRInfo.get());
            } catch (Exception ex) {
                ex.printStackTrace();
                return new ResponseEntity<>(new ErrorResponse<>("Failed to update HR info"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(new SuccessResponse<>("Successfully updated HR info"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteHRInfo(long id) {
        try {
            hrInfoRepository.deleteById(id);
            return new ResponseEntity<>(new SuccessResponse<>("Successfully deleted HR info"), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse<>("Failed to delete HR info"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
