package com.synesis.hrmis.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;

    private String fullName;

    private String phoneNumber;

    private String officialEmail;

    private String personalEmail;

    private Long emergencyContact;

    private String nationality;

    private Long identificationNo;

    private LocalDate birthDate;

    private String birthPlace;

    private String gender;

    private String religion;

    private String maritalStatus;

    private String bloodGroup;

    private long departmentId;

    private long designationId;

    private String employmentType;

    private LocalDate joiningDate;

    private String employeePhoto;

    private String createdBy;

    private LocalDateTime createdAt;

    private String lastModifiedBy;

    private LocalDateTime lastModifiedAt;
}
