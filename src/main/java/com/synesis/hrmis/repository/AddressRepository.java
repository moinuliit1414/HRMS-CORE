package com.synesis.hrmis.repository;

import com.synesis.hrmis.domain.Address;
import com.synesis.hrmis.dto.responseDTO.AttendanceCountResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAddressByEmployeeIdOrderByCreatedAt(long employeeId);
    Optional<Address> findAddressByIdAndEmployeeId(long id, long employeeId);
    Address deleteAddressByEmployeeId(long employeeId);
    @Query(nativeQuery = true,value = "select employee_id as employeeId,employee_name as employeeName,count(in_time) as " +
            "employeeCount from public.attendance_info where att_date between ?1 and ?2 group by employee_id,employee_name")
    List<AttendanceCountResponse> findEmployeeWiseAttendanceCount(LocalDate startDate, LocalDate endDate);
}
