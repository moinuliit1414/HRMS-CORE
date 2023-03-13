package com.synesis.hrmis.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveTypeRequest {

    private String leaveType;

    private String gender;

    private Integer days;

    @JsonProperty
    private boolean isHalfDayApplicable;

    @JsonProperty
    private boolean isPaidLeave;

    private Integer maxLifeTimeLeave;
}
