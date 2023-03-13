package com.synesis.hrmis.service.impl;

import com.synesis.hrmis.domain.Address;
import com.synesis.hrmis.domain.Employee;
import com.synesis.hrmis.domain.LeaveBalance;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.EmployeeRepository;
import com.synesis.hrmis.repository.LeaveBalanceRepository;
import com.synesis.hrmis.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Long  paternalLeave = 5L;
    private final Long  maternalLeave = 112L;
    private final Long matrimonialLeave = 5L;
    private final Long earnedLeave = 0L;
    private final Long leaveWithoutPay = 0L;

    private final LeaveBalanceRepository leaveBalanceRepository;

    private final EmployeeRepository employeeRepository;

    ModelMapper mapper = new ModelMapper();

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               LeaveBalanceRepository leaveBalanceRepository
                               ) {
        this.employeeRepository = employeeRepository;
        this.leaveBalanceRepository = leaveBalanceRepository;
    }

    @Override
    public ResponseEntity<?> saveEmployee(Employee employeeInfo) {
        try {
            Employee employee = employeeRepository.save(employeeInfo);
            calculateLeaves(employee);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(new ErrorResponse("Failed to save employee data"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(new SuccessResponse("Successfully saved employee data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getEmployeeById(long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            return new ResponseEntity(new SuccessResponse(employee), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Failed to get employee data"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        return new ResponseEntity(new SuccessResponse(employeeList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateEmployee(Employee employeeInfo) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employeeInfo.getId());
        if(existingEmployee.isPresent()) {
            try {
                mapper.map(employeeInfo, existingEmployee.get());
                employeeRepository.save(existingEmployee.get());
            } catch (Exception ex) {
                ex.printStackTrace();
                return new ResponseEntity<>(new ErrorResponse<>("Failed to update employee data"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(new SuccessResponse<>("Successfully updated employee data"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteEmployee(long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isPresent()) {
            employeeRepository.delete(employee.get());
            return new ResponseEntity(new SuccessResponse("Successfully deleted employee data"), HttpStatus.OK);
        }
        return new ResponseEntity(new ErrorResponse("Employee data does not exist"), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> getAllEmployeeName() {
        List<String> employeeNameList = employeeRepository.findAllEmployeeName();
        return new ResponseEntity(new SuccessResponse(employeeNameList), HttpStatus.OK);
    }

    private void calculateLeaves(Employee employeeInfo) {
        LocalDate now = LocalDate.now();
        LocalDate endDateOfYear = now.with(TemporalAdjusters.lastDayOfYear());
        Integer dayDiff = endDateOfYear.getDayOfYear() - now.getDayOfYear();
        Long cl = Long.valueOf((10*dayDiff)/365);
        Long sl = Long.valueOf((14*dayDiff)/365);
        LeaveBalance leaveBalance = new LeaveBalance();
        leaveBalance.setEmployeeId(employeeInfo.getId());
        leaveBalance.setCasualLeave(cl);
        leaveBalance.setSickLeave(sl);
        if(employeeInfo.getGender().equals("Male")){
            leaveBalance.setPaternalLeave(paternalLeave);
        } else {
            leaveBalance.setMaternalLeave(maternalLeave);
        }
        leaveBalance.setMatrimonialLeave(matrimonialLeave);
        leaveBalance.setEarnedLeave(earnedLeave);
        leaveBalance.setLeaveWithoutPay(leaveWithoutPay);
        leaveBalanceRepository.save(leaveBalance);
    }
}
