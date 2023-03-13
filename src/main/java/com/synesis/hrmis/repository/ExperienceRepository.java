package com.synesis.hrmis.repository;

import com.synesis.hrmis.domain.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<Experience> findExperienceByEmployeeId(long employeeId);
    Optional<Experience> findExperienceByIdAndEmployeeId(long id, long employeeId);
    Experience deleteExperienceByEmployeeId(long employeeId);
}
