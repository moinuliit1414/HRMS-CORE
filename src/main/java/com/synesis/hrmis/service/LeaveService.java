package com.synesis.hrmis.service;

import com.synesis.hrmis.dto.requestDTO.LeaveApplicationRequest;
import com.synesis.hrmis.dto.requestDTO.LeaveTypeRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface LeaveService {
    ResponseEntity<?> saveLeaveType(LeaveTypeRequest leaveType);
    ResponseEntity<?> getLeaveTypes();
    ResponseEntity<?> getLeaveTypeByGender(String gender);
    ResponseEntity<?> saveLeaveBalance(Object leaveBalance);
    ResponseEntity<?> getLeaveBalances();
    ResponseEntity<?> getLeaveBalanceByEmployeeId(Long employeeId);
    ResponseEntity<?> saveLeaveApplication(LeaveApplicationRequest leaveApplication);
    ResponseEntity<?> getAllLeaveApplications();
    ResponseEntity<?> approveLeaveApplication(Long leaveApplicationId,Integer status);
}
