package com.synesis.hrmis.repository;

import com.synesis.hrmis.domain.Address;
import com.synesis.hrmis.domain.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findEducationsByEmployeeId(long employeeId);
    Optional<Education> findEducationsByIdAndEmployeeId(long id, long employeeId);
    Education deleteEducationByEmployeeId(long employeeId);
}
