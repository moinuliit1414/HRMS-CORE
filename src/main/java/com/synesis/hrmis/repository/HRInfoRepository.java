package com.synesis.hrmis.repository;

import com.synesis.hrmis.domain.HRInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HRInfoRepository extends JpaRepository<HRInfo, Long> {
    List<HRInfo> findHRInfoByEmployeeId(long employeeId);
    Optional<HRInfo> findHRInfoByIdAndEmployeeId(long id, long employeeId);
    HRInfo deleteHRInfoByEmployeeId(long employeeId);
}
