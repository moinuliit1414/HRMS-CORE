package com.synesis.hrmis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sys_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_id")
    @SequenceGenerator(name = "emp_id", initialValue = 10001, allocationSize = 1)
    private Long id;

    @Column(name = "previous_emp_id")
    private Long previousEmpId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "official_email")
    private String officialEmail;

    @Column(name = "personal_email")
    private String personalEmail;

    @Column(name = "emergency_contact")
    private Long emergencyContact;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "identification_no")
    private Long identificationNo;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "gender")
    private String gender;

    @Column(name = "religion")
    private String religion;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "blood_group")
    private String bloodGroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "designation_id")
    private Designation designation;

    @Column(name = "employment_type")
    private String employmentType;

    @Column(name = "joining_date")
    private LocalDate joiningDate;

    @Column(name = "employee_photo")
    private String employeePhoto;

//    @ManyToOne(fetch = FetchType.EAGER)
//    private Department department;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Designation designation;

    //need to implement supervisor table
    //@ManyToOne(fetch = FetchType.EAGER)
    //private Supervisor supervisor;

    //need to implement Gender table
    //@ManyToOne(fetch = FetchType.EAGER)
    //private Gender gender;

    //need to implement District table
    //@ManyToOne(fetch = FetchType.EAGER)
    //private District district;

    //need to implement Marital_status table
    //@ManyToOne(fetch = FetchType.EAGER)
    //private MaritalStatus maritalStatus;

    //need to implement Religion table
    //@ManyToOne(fetch = FetchType.EAGER)
    //private Religion religion;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;
}
