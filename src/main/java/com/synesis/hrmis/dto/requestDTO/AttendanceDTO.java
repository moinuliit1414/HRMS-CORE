package com.synesis.hrmis.dto.requestDTO;

import com.synesis.hrmis.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO {

    private String attDate;

    private String attId;

    private String firstInTime;

    private String lastOutTime;

    private String duration;

    private String synEmpId;

    private String empName;
}
