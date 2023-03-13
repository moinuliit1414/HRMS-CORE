package com.synesis.hrmis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "education_info")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "level")
    private String level;

    @Column(name = "institue")
    private String institute;

    @Column(name = "subject")
    private String subject;

    @Column(name = "passing_year")
    private String passingYear;

    @Column(name = "result_type")
    private String resultType;

    @Column(name = "result")
    private String result;
}
