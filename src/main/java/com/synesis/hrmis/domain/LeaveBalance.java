package com.synesis.hrmis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "leave_balance")
public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "casual_leave")
    private Long casualLeave;

    @Column(name = "sick_leave")
    private Long sickLeave;

    @Column(name = "maternal_leave")
    private Long maternalLeave;

    @Column(name = "paternal_leave")
    private Long paternalLeave;

    @Column(name = "earned_leave")
    private Long earnedLeave;

    @Column(name = "matrimonial_leave")
    private Long matrimonialLeave;

    @Column(name = "leave_without_pay")
    private Long leaveWithoutPay;

}
