package com.synesis.hrmis.scheduler;

import com.synesis.hrmis.service.AttendanceService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyAttendanceScheduler {

    private final AttendanceService attendanceService;

    public DailyAttendanceScheduler(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @Scheduled(cron = "0 30 23 * * ?")
    public void saveDailyAttendanceData() {
        attendanceService.saveAttendanceInfo();
    }
}
