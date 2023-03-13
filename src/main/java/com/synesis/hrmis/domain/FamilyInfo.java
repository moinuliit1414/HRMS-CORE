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
@Table(name = "family_info")
public class FamilyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "relation")
    private String relation;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "identification_no")
    private String identificationNo;

    @Column(name = "contact")
    private String contact;


}
