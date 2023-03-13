package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.Department;
import com.synesis.hrmis.domain.Designation;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.DepartmentRepository;
import com.synesis.hrmis.repository.DesignationRepository;
import com.synesis.hrmis.service.DesignationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class DesignationServiceImpl implements DesignationService {

    private final DesignationRepository designationRepository;

    private final DepartmentRepository departmentRepository;

    ModelMapper mapper = new ModelMapper();

    public DesignationServiceImpl(DesignationRepository designationRepository,
            DepartmentRepository departmentRepository) {
        this.designationRepository = designationRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public ResponseEntity<?> getAllDesignation() {
        List<Designation> designations = designationRepository.findAll();
        return new ResponseEntity(new SuccessResponse(designations), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllDesignationById(long designationId) {
        Optional<Designation> designation = designationRepository.findById(designationId);
        if (designation.isPresent()) {
            return new ResponseEntity(new SuccessResponse(designation), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Failed to get data"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getDesignationByDepartmentId(long departmentId) {
        List<Designation> designations = designationRepository.findDesignationByDepartment_Id(departmentId);
        if (!designations.isEmpty()) {
            return new ResponseEntity<>(new SuccessResponse<>(designations), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse<>("No Designation Data Found"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveDesignationInfo(Object designationInfo) {
        Designation designation = mapper.map(designationInfo, Designation.class);
        try {
            designationRepository.save(designation);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to save Data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new SuccessResponse("Successfully saved Designation Data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateDesignationInfo(long designationId,@RequestBody Object designationNewData) {
        Optional<Designation> designationExistData = designationRepository.findById(designationId);
        if (designationExistData.isPresent()) {
            mapper.map(designationNewData, designationExistData.get());
            return new ResponseEntity<>(new SuccessResponse<>(designationRepository.save(designationExistData.get())), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> deleteDesignation(long designationId) {
        Optional<Designation> designation = designationRepository.findById(designationId);
        if (designation.isPresent()) {
            designationRepository.delete(designation.get());
            return new ResponseEntity(new SuccessResponse("Successfully deleted designation data"), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Designation Data is not Exists."), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getAllDesignationByDepartmentId(long departmentId) {
        List<Designation> designations = designationRepository.findDesignationByDepartment_Id(departmentId);
        return new ResponseEntity(new SuccessResponse(designations), HttpStatus.OK);
    }
}
