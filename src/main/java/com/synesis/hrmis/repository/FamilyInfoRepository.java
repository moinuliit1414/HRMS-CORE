package com.synesis.hrmis.repository;

import com.synesis.hrmis.domain.Address;
import com.synesis.hrmis.domain.FamilyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FamilyInfoRepository extends JpaRepository<FamilyInfo, Long> {
    List<FamilyInfo> findFamilyInfoByEmployeeId(long employeeId);
    Optional<FamilyInfo> findFamilyInfoByIdAndEmployeeId(long id, long employeeId);
    FamilyInfo deleteFamilyInfoByEmployeeId(long employeeId);
}
