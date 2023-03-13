package com.synesis.hrmis.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveApplicationRequest {
    private Long employeeId;

    private Long leaveTypeId;

    private LocalDate applicableFrom;

    private LocalDate applicableTo;

    private Integer noOfDays;
}
