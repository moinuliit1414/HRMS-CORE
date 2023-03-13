package com.synesis.hrmis.controller;

import com.synesis.hrmis.domain.Address;
import com.synesis.hrmis.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/attendance/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return attendanceService.uploadAttendanceFile(file);
    }

    @GetMapping("/attendance")
    public ResponseEntity<?> getAttendanceInformation(@RequestParam("from") String from, @RequestParam("to") String to,
                                                      @RequestParam("employeeName") String employeeName) {
        return attendanceService.getAttendanceInfo(from, to, employeeName);
    }

    @GetMapping("/daily-attendance")
    public ResponseEntity<?> getAttendanceInformation() {
        return attendanceService.getDailyAttendanceData();
    }

    @PostMapping("/daily-attendance")
    public ResponseEntity<?> saveDailyAttendanceData() {
        return attendanceService.saveAttendanceInfo();
    }
}
