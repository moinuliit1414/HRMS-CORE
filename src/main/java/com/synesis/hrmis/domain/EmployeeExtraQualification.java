package com.synesis.hrmis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "extra_qualification_info")
public class EmployeeExtraQualification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "qualification_title")
    private String qualificationTitle;

    @Column(name = "institute")
    private String institute;

    @Column(name = "certification_number")
    private String certificationNumber;

    @Column(name = "duration")
    private String duration;

}
