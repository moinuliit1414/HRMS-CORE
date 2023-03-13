package com.synesis.hrmis.repository;

import com.synesis.hrmis.domain.Attendance;
import com.synesis.hrmis.domain.AttendanceView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceViewRepository extends JpaRepository<AttendanceView, Long> {

}
