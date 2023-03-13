package com.synesis.hrmis.dto.requestDTO;

import com.synesis.hrmis.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyAttendanceUpdateDTO {

    private String requestId;

    private String requestTs;

    private String requestType;

    private List<AttendanceDTO> requestMessage;

}
