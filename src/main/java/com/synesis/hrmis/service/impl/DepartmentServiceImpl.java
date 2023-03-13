package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.Department;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.DepartmentRepository;
import com.synesis.hrmis.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    ModelMapper mapper = new ModelMapper();

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public ResponseEntity<?> getAllDepartment() {
        List<Department> departmentList = departmentRepository.findAll();
        return new ResponseEntity(new SuccessResponse(departmentList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getDepartmentById(long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            return new ResponseEntity(new SuccessResponse(department), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Failed to get Employee Data"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> saveDepartmentInfo(Object departmentInfo) {
        Department department = mapper.map(departmentInfo, Department.class);
        try {
            departmentRepository.save(department);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to save Department Data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new SuccessResponse("Successfully saved Department Data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateDepartmentInfo(long departmentId, Object departmentNewData) {
        Optional<Department> departmentExistData = departmentRepository.findById(departmentId);
        if (departmentExistData.isPresent()) {
            mapper.map(departmentNewData, departmentExistData.get());
            return new ResponseEntity<>(new SuccessResponse<>(departmentRepository.save(departmentExistData.get())), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ErrorResponse("Internal Problem to Updated Department Data"),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<?> deleteDepartment(long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            departmentRepository.delete(department.get());
            return new ResponseEntity(new SuccessResponse("Successfully deleted employee data"), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Employee data does not exist"), HttpStatus.NOT_FOUND);
    }
}
