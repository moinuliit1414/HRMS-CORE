package com.synesis.hrmis.controller;

import com.synesis.hrmis.dto.requestDTO.LeaveApplicationRequest;
import com.synesis.hrmis.dto.requestDTO.LeaveTypeRequest;
import com.synesis.hrmis.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LeaveController {

    @Autowired
    private  LeaveService service;

    @PostMapping("/leave-type")
    public ResponseEntity<?> saveLeaveType(@RequestBody LeaveTypeRequest request) {
        return service.saveLeaveType(request);
    }

    @GetMapping("/leave-type")
    public ResponseEntity<?> getLeaveTypes() {
        return service.getLeaveTypes();
    }

    @GetMapping ("/leave-type/{gender}")
    public ResponseEntity<?> getLeaveTypeByGender(@PathVariable(name = "gender") String gender) {
        return service.getLeaveTypeByGender(gender);
    }

    @PostMapping("/leave-balance")
    public ResponseEntity<?> saveLeaveBalance(@RequestBody Object leaveBalance) {
        return service.saveLeaveBalance(leaveBalance);
    }

    @GetMapping("/leave-balance")
    public ResponseEntity<?> getLeaveBalance() {
        return service.getLeaveBalances();
    }

    @GetMapping("/leave-balance/{employeeId}")
    public ResponseEntity<?> getLeaveBalanceByEmployeeId(@PathVariable(name = "employeeId") String employeeId) {
        Long employeeIdValue = Long.parseLong(employeeId);
        return service.getLeaveBalanceByEmployeeId(employeeIdValue);
    }

    @PostMapping("/leave-application")
    public ResponseEntity<?> saveLeaveApplication(@RequestBody LeaveApplicationRequest leaveApplication) {
        return  service.saveLeaveApplication(leaveApplication);
    }

    @GetMapping("/leave-application")
    public ResponseEntity<?> getAllLeaveApplication() {
        return  service.getAllLeaveApplications();
    }

    @PostMapping("/leave-application-approval")
    public ResponseEntity<?> leaveApplicationApproval(@RequestParam Long leaveApplicationId,@RequestParam Integer status) {
        return  service.approveLeaveApplication(leaveApplicationId,status);
    }
}
