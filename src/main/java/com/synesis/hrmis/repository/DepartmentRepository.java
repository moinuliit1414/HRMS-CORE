package com.synesis.hrmis.repository;

import com.synesis.hrmis.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
