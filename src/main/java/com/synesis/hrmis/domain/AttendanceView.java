package com.synesis.hrmis.domain;

import lombok.*;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Subselect("select e.id, v.name, d.long_name as designation, v.attn_date, v.in_time, v.out_time from v_sil_daly_att_report v " +
        "INNER join sys_employee e on v.emp_no = e.id " +
        "INNER join designation d ON d.id = e.designation_id")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceView {

    @Id
    @Column(name = "id")
    private Long employeeId;

    @Column(name = "name")
    private String employeeName;

    @Column(name = "designation")
    private String designation;

    @Column(name = "attn_date")
    private LocalDate attendanceDate ;

    @Column(name = "in_time")
    private LocalTime inTime;

    @Column(name = "out_time")
    private LocalTime outTime;
}
