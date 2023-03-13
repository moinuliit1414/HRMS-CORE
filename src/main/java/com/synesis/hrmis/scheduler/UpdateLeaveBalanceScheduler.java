package com.synesis.hrmis.scheduler;

import com.synesis.hrmis.domain.LeaveBalance;
import com.synesis.hrmis.dto.responseDTO.AttendanceCountResponse;
import com.synesis.hrmis.repository.AddressRepository;
import com.synesis.hrmis.repository.LeaveBalanceRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class UpdateLeaveBalanceScheduler {

    private final AddressRepository addressRepository;
    private final LeaveBalanceRepository balanceRepository;
    private final Long casualLeave = 10L;
    private final Long sickLeave = 14L;

    public UpdateLeaveBalanceScheduler(AddressRepository addressRepository,
                                       LeaveBalanceRepository balanceRepository) {
        this.addressRepository = addressRepository;
        this.balanceRepository = balanceRepository;
    }

//    @Scheduled(cron = "*/10 * * * * *")
//    public void scheduleLeaveBalance() {
//        LocalDate now = LocalDate.now();
//        LocalDate prev = now.minusYears(1);
//        List<AttendanceCountResponse> attendanceCountResponseList = addressRepository.findEmployeeWiseAttendanceCount(prev,now);
//        attendanceCountResponseList.forEach(x->{
//            LeaveBalance balance = balanceRepository.findLeaveBalanceByEmployeeId(x.getEmployeeId());
//            if(balance !=null){
//                Long earnedLeave = Long.valueOf(x.getEmployeeCount()/36) + balance.getEarnedLeave();
//                if(earnedLeave>40){
//                    earnedLeave = 40L;
//                }
//                balance.setEarnedLeave(earnedLeave);
//                balance.setCasualLeave(casualLeave);
//                balance.setSickLeave(sickLeave);
//                balance.setMatrimonialLeave(balance.getMatrimonialLeave());
//                balance.setPaternalLeave(balance.getPaternalLeave());
//                balance.setMaternalLeave(balance.getMaternalLeave());
//                balance.setLeaveWithoutPay(balance.getLeaveWithoutPay());
//                balanceRepository.save(balance);
//            }
//        });
//    }

    @Scheduled(cron = "0 0 0 31 12 ?")
    public void scheduleLeaveBalanceUsingCronExpression() {
        LocalDate now = LocalDate.now();
        LocalDate prev = now.minusYears(1);
        List<AttendanceCountResponse> attendanceCountResponseList = addressRepository.findEmployeeWiseAttendanceCount(prev,now);
        attendanceCountResponseList.forEach(x->{
            LeaveBalance balance = balanceRepository.findLeaveBalanceByEmployeeId(x.getEmployeeId());
            if(balance !=null){
                Long earnedLeave = Long.valueOf(x.getEmployeeCount()/36) + balance.getEarnedLeave();
                if(earnedLeave>40){
                    earnedLeave = 40L;
                }
                balance.setEarnedLeave(earnedLeave);
                balance.setCasualLeave(casualLeave);
                balance.setSickLeave(sickLeave);
                balance.setMatrimonialLeave(balance.getMatrimonialLeave());
                balance.setPaternalLeave(balance.getPaternalLeave());
                balance.setMaternalLeave(balance.getMaternalLeave());
                balance.setLeaveWithoutPay(balance.getLeaveWithoutPay());
                balanceRepository.save(balance);
            }
        });
    }
}
