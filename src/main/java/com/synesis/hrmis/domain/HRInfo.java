package com.synesis.hrmis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hr_info")
public class HRInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "salary_unit")
    private String salaryUnit;

    @Column(name = "salary_configuration")
    private String salaryConfiguration;

    @Column(name = "salary_grade")
    private String salaryGrade;

    @Column(name = "basic_salary")
    private String basicSalary;

    @Column(name = "gross_salary")
    private String grossSalary;

    @Column(name = "sbu")
    private String sbu;

    @Column(name = "permanent_date")
    private LocalDate permanentDate;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "service_status")
    private String serviceStatus;

    @Column(name = "location")
    private String jobLocation;

    @Column(name = "in_time")
    private String inTime;

    @Column(name = "out_time")
    private String outTime;
}
