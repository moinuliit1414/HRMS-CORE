package com.synesis.hrmis.repository;

import com.synesis.hrmis.domain.LeaveType;
import com.synesis.hrmis.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveTypeRepository extends JpaRepository<LeaveType,Long> {
    List<LeaveType> findByGenderNot(Gender gender);
}
