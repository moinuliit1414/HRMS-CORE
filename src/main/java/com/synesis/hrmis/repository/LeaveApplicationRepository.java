package com.synesis.hrmis.repository;

import com.synesis.hrmis.domain.LeaveApplication;
import com.synesis.hrmis.enums.LeaveApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication,Long> {
    List<LeaveApplication> findAllByStatus(LeaveApplicationStatus status);
}
