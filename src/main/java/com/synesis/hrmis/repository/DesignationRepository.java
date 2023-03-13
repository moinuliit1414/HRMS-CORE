package com.synesis.hrmis.repository;

import com.synesis.hrmis.domain.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesignationRepository extends JpaRepository<Designation, Long> {
    List<Designation> findDesignationByDepartment_Id(Long departmentId);
}
