package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.LeaveApplication;
import com.synesis.hrmis.domain.LeaveBalance;
import com.synesis.hrmis.domain.LeaveType;
import com.synesis.hrmis.dto.requestDTO.LeaveApplicationRequest;
import com.synesis.hrmis.dto.requestDTO.LeaveTypeRequest;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.enums.Gender;
import com.synesis.hrmis.enums.LeaveApplicationStatus;
import com.synesis.hrmis.repository.LeaveApplicationRepository;
import com.synesis.hrmis.repository.LeaveBalanceRepository;
import com.synesis.hrmis.repository.LeaveTypeRepository;
import com.synesis.hrmis.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Primary
@RequiredArgsConstructor
@Transactional
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    @Autowired
    private LeaveBalanceRepository balanceRepository;

    @Autowired
    private LeaveApplicationRepository applicationRepository;

    ModelMapper mapper = new ModelMapper();

    @Override
    public ResponseEntity<?> saveLeaveType(LeaveTypeRequest request) {
        LeaveType type = mapper.map(request, LeaveType.class);
        try{
            leaveTypeRepository.save(type);
        } catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to save leave type"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new SuccessResponse("Successfully saved leave type"), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getLeaveTypes() {
        List<LeaveType> leaveTypes;
        try {
            leaveTypes = leaveTypeRepository.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to get leave types"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(leaveTypes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getLeaveTypeByGender(String gender) {
        List<LeaveType> leaveTypes;
        try {
            Gender genderNotValue = (gender.equals("Male")) ? Gender.female: Gender.male;
            leaveTypes = leaveTypeRepository.findByGenderNot(genderNotValue);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to save leave type"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(leaveTypes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveLeaveBalance(Object leaveBalance) {
        LeaveBalance balance = mapper.map(leaveBalance, LeaveBalance.class);
        try{
            balanceRepository.save(balance);
        } catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to save leave balance"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new SuccessResponse("Successfully saved leave balance"), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getLeaveBalances() {
        List<LeaveBalance> leaveBalances;
        try {
            leaveBalances = balanceRepository.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to save leave type"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(leaveBalances, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getLeaveBalanceByEmployeeId(Long employeeId) {
        LeaveBalance leaveBalance;
        try {
            leaveBalance = balanceRepository.findLeaveBalanceByEmployeeId(employeeId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to save leave type"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(leaveBalance, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> saveLeaveApplication(LeaveApplicationRequest leaveApplication) {
        LeaveApplication application = mapper.map(leaveApplication, LeaveApplication.class);
        try{
            application.setStatus(LeaveApplicationStatus.pending);
            application.setCreatedAt(LocalDateTime.now());
            applicationRepository.save(application);
        } catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to save leave application"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new SuccessResponse("Successfully saved leave application"), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getAllLeaveApplications() {
        List<LeaveApplication> leaveApplications;
        try{
            leaveApplications = applicationRepository.findAllByStatus(LeaveApplicationStatus.pending);
        } catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to get leave applications"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(leaveApplications, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> approveLeaveApplication(Long leaveApplicationId, Integer status) {
        Optional<LeaveApplication> leaveApplication = applicationRepository.findById(leaveApplicationId);
        if(status==2){
            leaveApplication.get().setStatus(LeaveApplicationStatus.rejected);
        } else if(status==1){
            Optional<LeaveType> leaveType = leaveTypeRepository.findById(leaveApplication.get().getLeaveTypeId());
            LeaveBalance leaveBalance = balanceRepository.findLeaveBalanceByEmployeeId(leaveApplication.get().getEmployeeId());
            switch (leaveType.get().getLeaveType()) {
                case "Casual Leave":
                    if(leaveApplication.get().getNoOfDays()<=leaveBalance.getCasualLeave()){
                        leaveBalance.setCasualLeave(leaveBalance.getCasualLeave()-leaveApplication.get().getNoOfDays());
                    } else {
                        return new ResponseEntity(new ErrorResponse("You do not have enough casual leaves"), HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    break;
                case "Sick Leave":
                    if(leaveApplication.get().getNoOfDays()<=leaveBalance.getSickLeave()){
                        leaveBalance.setSickLeave(leaveBalance.getSickLeave()-leaveApplication.get().getNoOfDays());
                    } else {
                        return new ResponseEntity(new ErrorResponse("You do not have enough sick leaves"), HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    break;
                case "Maternity Leave":
                    if(leaveApplication.get().getNoOfDays()<=leaveBalance.getMaternalLeave()){
                        leaveBalance.setMaternalLeave(leaveBalance.getMaternalLeave()-leaveApplication.get().getNoOfDays());
                    } else {
                        return new ResponseEntity(new ErrorResponse("You do not have enough maternal leaves"), HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    break;
                case "Paternity Leave":
                    if(leaveApplication.get().getNoOfDays()<=leaveBalance.getPaternalLeave()){
                        leaveBalance.setPaternalLeave(leaveBalance.getPaternalLeave()-leaveApplication.get().getNoOfDays());
                    } else {
                        return new ResponseEntity(new ErrorResponse("You do not have enough paternal leaves"), HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    break;
                case "Earned Leave":
                    if(leaveApplication.get().getNoOfDays()<=leaveBalance.getEarnedLeave()){
                        leaveBalance.setEarnedLeave(leaveBalance.getEarnedLeave()-leaveApplication.get().getNoOfDays());
                    } else {
                        return new ResponseEntity(new ErrorResponse("You do not have enough earned leaves"), HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    break;
                case "Matrimonial Leave":
                    if(leaveApplication.get().getNoOfDays()<=leaveBalance.getMatrimonialLeave()){
                        leaveBalance.setMatrimonialLeave(leaveBalance.getMatrimonialLeave()-leaveApplication.get().getNoOfDays());
                    } else {
                        return new ResponseEntity(new ErrorResponse("You do not have enough matrimonial leaves"), HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    break;
                case "Leave Without Pay":
                    leaveBalance.setLeaveWithoutPay(Long.valueOf(leaveApplication.get().getNoOfDays()));
                    break;
            }
            leaveApplication.get().setStatus(LeaveApplicationStatus.approved);
            balanceRepository.save(leaveBalance);
        }
        applicationRepository.save(leaveApplication.get());
        return new ResponseEntity(leaveApplication.get(), HttpStatus.OK);
    }
}
