package com.synesis.hrmis.domain;

import com.synesis.hrmis.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "leave_type")
public class LeaveType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "leave_type")
    private String leaveType;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "no_of_days")
    private Integer days;

    @Column(name = "is_half_day_applicable")
    private boolean isHalfDayApplicable;

    @Column(name = "is_paid_leave")
    private boolean isPaidLeave;

    @Column(name = "max_lifetime_leave")
    private Integer maxLifeTimeLeave;

}
