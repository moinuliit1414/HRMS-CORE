package com.synesis.hrmis.service;

import com.synesis.hrmis.domain.Address;
import com.synesis.hrmis.domain.Attendance;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public interface AttendanceService {
    ResponseEntity<?> saveAttendanceInfo();
    ResponseEntity<?> uploadAttendanceFile(MultipartFile attendanceFile) throws IOException;
    ResponseEntity<?> getAttendanceInfo(String from, String to, String employeeName);
    ResponseEntity<?> getDailyAttendanceData();

}
