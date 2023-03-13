package com.synesis.hrmis.repository;

import com.synesis.hrmis.domain.LeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance,Long> {
    LeaveBalance findLeaveBalanceByEmployeeId(Long employeeId);
}
