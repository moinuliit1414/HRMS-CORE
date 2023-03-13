package com.synesis.hrmis.controller;

import com.synesis.hrmis.domain.HRInfo;
import com.synesis.hrmis.service.HRInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HRInfoController {

    private final HRInfoService hrInfoService;

    public HRInfoController(HRInfoService hrInfoService) {
        this.hrInfoService = hrInfoService;
    }

    @GetMapping("/hr-info")
    public ResponseEntity<?> getAllHRInfo() {
        return hrInfoService.getAllHRInfo();
    }

    @GetMapping("/hr-info/{id}")
    public ResponseEntity<?> getHRInfo(@PathVariable(name = "id") long employeeId) {
        return hrInfoService.getHRInfo(employeeId);
    }

    @PostMapping("/hr-info")
    public ResponseEntity<?> saveHRInfo(@RequestBody HRInfo hrInfo) {
        return hrInfoService.saveHRInfo(hrInfo);
    }

    @PutMapping("/hr-info")
    public ResponseEntity<?> updateHRInfo(@RequestBody HRInfo hrInfo) {
        return hrInfoService.updateHRInfo(hrInfo);
    }

    @DeleteMapping("/hr-info/{id}")
    public ResponseEntity<?> deleteHRInfo(@PathVariable(name = "id") long id) {
        return hrInfoService.deleteHRInfo(id);
    }
}