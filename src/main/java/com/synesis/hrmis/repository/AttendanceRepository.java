package com.synesis.hrmis.repository;

import com.synesis.hrmis.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAttendanceByAttendanceDate(LocalDate attendanceDate);
    @Query(nativeQuery = true, value = "SELECT * from ATTENDANCE_INFO WHERE ATT_DATE BETWEEN :from AND :to " +
            "AND EMPLOYEE_NAME = :employeeName ORDER BY ATT_DATE DESC")
    List<Attendance> findAttendanceByDateRangeAndEmployeeName(@Param("from") LocalDate from, @Param("to") LocalDate to,
                                                              @Param("employeeName") String employeeName);
    @Query(nativeQuery = true, value = "SELECT * from ATTENDANCE_INFO WHERE ATT_DATE BETWEEN :from AND :to ORDER BY ATT_DATE DESC")
    List<Attendance> findAttendanceByDateRange(@Param("from") LocalDate from, @Param("to") LocalDate to);

}
