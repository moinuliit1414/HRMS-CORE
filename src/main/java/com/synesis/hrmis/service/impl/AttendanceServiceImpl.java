package com.synesis.hrmis.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.synesis.hrmis.domain.Attendance;
import com.synesis.hrmis.domain.AttendanceView;
import com.synesis.hrmis.dto.responseDTO.ErrorResponse;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.repository.AttendanceRepository;
import com.synesis.hrmis.repository.AttendanceViewRepository;
import com.synesis.hrmis.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final AttendanceViewRepository attendanceViewRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, AttendanceViewRepository attendanceViewRepository) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceViewRepository = attendanceViewRepository;
    }

    @Override
    public ResponseEntity<?> saveAttendanceInfo() {
        List<Attendance> attendanceList = new ArrayList<>();
        List<AttendanceView> attendanceViewList = attendanceViewRepository.findAll();
        for(AttendanceView attendanceView : attendanceViewList) {
            Attendance attendance = Attendance.builder()
                    .employeeName(attendanceView.getEmployeeName())
                    .designation(attendanceView.getDesignation())
                    .employeeId(attendanceView.getEmployeeId())
                    .attendanceDate(attendanceView.getAttendanceDate())
                    .inTime(attendanceView.getInTime())
                    .outTime(attendanceView.getOutTime())
                    .duration(LocalTime.ofSecondOfDay(attendanceView.getOutTime().toSecondOfDay()
                            - attendanceView.getInTime().toSecondOfDay()))
                    .status(setAttendanceStatus(attendanceView.getInTime()))
                    .build();
            attendanceList.add(attendance);
        }
        try {
            attendanceRepository.saveAll(attendanceList);
            return new ResponseEntity<>(new SuccessResponse<>("Successfully saved attendance info"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ErrorResponse<>("Failed to save attendance info"), HttpStatus.OK);

        }
    }

    @Override
    public ResponseEntity<?> uploadAttendanceFile(MultipartFile attendanceFile) throws IOException {
        List<Attendance> attendanceList = new ArrayList<>();

        try {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern("MM-dd-yyyy HH:mm:ss").toFormatter();

            Reader reader = new InputStreamReader(attendanceFile.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                Attendance attendance = new Attendance();
                if (!nextRecord[0].isBlank()) {
                    nextRecord[0] = nextRecord[0].replaceAll(",", "");
                    attendance.setEmployeeId(Long.valueOf(nextRecord[0]));
                }
                if (!nextRecord[1].isBlank()) {
                    attendance.setEmployeeName(nextRecord[1]);
                }
                if (!nextRecord[2].isBlank()) {
                    LocalDateTime attendanceDate = LocalDateTime.parse(nextRecord[2], formatter);
                    attendance.setAttendanceDate(attendanceDate.toLocalDate());
                }
                if (!nextRecord[3].isBlank()) {
                    LocalDateTime inTime = LocalDateTime.parse(nextRecord[3], formatter);
                    attendance.setInTime(inTime.toLocalTime());
                }
                if (!nextRecord[4].isBlank()) {
                    LocalDateTime outTime = LocalDateTime.parse(nextRecord[4], formatter);
                    attendance.setOutTime(outTime.toLocalTime());
                }
                attendanceList.add(attendance);
            }

            attendanceRepository.saveAll(attendanceList);
            return new ResponseEntity<>(new SuccessResponse<>(attendanceList), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse<>("Failed to upload Attendance file"), HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<?> getAttendanceInfo(String from, String to, String employeeName) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-M-d").toFormatter();

        LocalDate dateFrom = LocalDate.parse(from, formatter);
        LocalDate dateTo = LocalDate.parse(to, formatter);
        List<Attendance> attendanceList;

        if(!employeeName.isEmpty()) {
            attendanceList = attendanceRepository.findAttendanceByDateRangeAndEmployeeName(dateFrom, dateTo, employeeName);
        } else {
            attendanceList = attendanceRepository.findAttendanceByDateRange(dateFrom, dateTo);

        }
        if(!attendanceList.isEmpty()) {
            return new ResponseEntity<>(new SuccessResponse<>(attendanceList), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse<>("Attendance Information not found"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getDailyAttendanceData() {
        List<AttendanceView> dailyAttendanceList = attendanceViewRepository.findAll();
        if(!dailyAttendanceList.isEmpty()) {
            return new ResponseEntity<>(new SuccessResponse<>(dailyAttendanceList), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse<>("Daily Attendance Data not found"), HttpStatus.OK);
    }

    private String setAttendanceStatus(LocalTime inTime) {
        if(inTime == null) {
            return "Absent";
        }
        else if (inTime.isAfter(LocalTime.of(9, 30))) {
            return "Late";
        } else {
            return "On Time";
        }
    }
}
