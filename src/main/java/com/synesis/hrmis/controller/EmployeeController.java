package com.synesis.hrmis.controller;

import com.synesis.hrmis.domain.Employee;
import com.synesis.hrmis.service.BasicInformationService;
import com.synesis.hrmis.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final BasicInformationService basicInformationService;

    public EmployeeController(EmployeeService employeeService, BasicInformationService basicInformationService) {
        this.employeeService = employeeService;
        this.basicInformationService = basicInformationService;
    }

    @GetMapping("/employee")
    public ResponseEntity<?> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable(name = "id") long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("/employee")
    public ResponseEntity<?> saveEmployeeInfo(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/employee")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employeeInfo) {
        return employeeService.updateEmployee(employeeInfo);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(name = "id") long employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/employee-names")
    public ResponseEntity<?> getAllEmployeeName() {
        return employeeService.getAllEmployeeName();
    }

    @GetMapping("/basic-info")
    public ResponseEntity<?> getEmployeeBasicInformation() {
        return basicInformationService.getAllBasicInfo();
    }


}