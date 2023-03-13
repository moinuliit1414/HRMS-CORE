package com.synesis.hrmis.repository;

import com.synesis.hrmis.domain.EmployeeExtraQualification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QualificationRepository extends JpaRepository<EmployeeExtraQualification, Long> {
    List<EmployeeExtraQualification> findEmployeeExtraQualificationByEmployeeId(long employeeId);
    Optional<EmployeeExtraQualification> findEmployeeExtraQualificationByIdAndEmployeeId(long id, long employeeId);
    EmployeeExtraQualification deleteEmployeeExtraQualificationByEmployeeId(long employeeId);
}
