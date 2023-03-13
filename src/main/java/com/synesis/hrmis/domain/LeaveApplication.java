package com.synesis.hrmis.domain;

import com.synesis.hrmis.enums.LeaveApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "leave_application")
public class LeaveApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "leave_type_id")
    private Long leaveTypeId;

    @Column(name = "applicable_from")
    private LocalDate applicableFrom;

    @Column(name = "applicable_to")
    private LocalDate applicableTo;

    @Column(name = "no_of_days")
    private Integer noOfDays;

    @Column(name = "status")
    private LeaveApplicationStatus status;

    @Column(name= "created_at")
    private LocalDateTime createdAt;
}
