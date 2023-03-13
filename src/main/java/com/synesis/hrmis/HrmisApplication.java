package com.synesis.hrmis;

import com.synesis.hrmis.domain.Action;
import com.synesis.hrmis.domain.AppUser;
import com.synesis.hrmis.domain.Privilege;
import com.synesis.hrmis.domain.Role;
import com.synesis.hrmis.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@EnableScheduling
@SpringBootApplication
public class HrmisApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrmisApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CommandLineRunner run(UserService userService) {
//        return args ->{
//
//            userService.saveUser(new AppUser(null, "Anindya Saha", "anindya@test.com", "1234", new ArrayList<>()));
//            userService.saveUser(new AppUser(null, "Shariful Alam", "shariful@test.com", "1234", new ArrayList<>()));
//            userService.saveUser(new AppUser(null, "Saydul Kader", "saydul@test.com", "1234", new ArrayList<>()));
//            userService.saveUser(new AppUser(null, "Marufur Rahman", "marufur@test.com", "1234", new ArrayList<>()));
//
//            userService.saveRole(new Role(null,"admin",new ArrayList<>()));
//            userService.saveRole(new Role(null, "user",new ArrayList<>()));
//            userService.saveRole(new Role(null, "manager",new ArrayList<>()));
//
//            userService.savePrivilege(new Privilege(null,"User",new ArrayList<>()));
//            userService.savePrivilege(new Privilege(null,"Role",new ArrayList<>()));
//            userService.savePrivilege(new Privilege(null,"Privilege",new ArrayList<>()));
//
//            userService.saveActions(new Action(null,"create",new ArrayList<>()));
//            userService.saveActions(new Action(null,"update",new ArrayList<>()));
//            userService.saveActions(new Action(null,"get",new ArrayList<>()));
//            userService.saveActions(new Action(null,"delete",new ArrayList<>()));
//
//            userService.addRoleToUser("anindya@test.com","admin");
//            userService.addRoleToUser("anindya@test.com","user");
//            userService.addRoleToUser("anindya@test.com","manager");
//            userService.addRoleToUser("shariful@test.com","user");
//            userService.addRoleToUser("shariful@test.com","manager");
//            userService.addRoleToUser("saydul@test.com","user");
//
//            userService.addPrivilegeToRole("admin","User");
//            userService.addPrivilegeToRole("admin","Role");
//            userService.addPrivilegeToRole("admin","Privilege");
//            userService.addPrivilegeToRole("manager","User");
//            userService.addPrivilegeToRole("manager","Role");
//            userService.addPrivilegeToRole("user","User");
//
//            userService.addActionToPrivilege("User","create");
//            userService.addActionToPrivilege("User","update");
//            userService.addActionToPrivilege("User","delete");
//            userService.addActionToPrivilege("User","get");
//            userService.addActionToPrivilege("Role","create");
//            userService.addActionToPrivilege("Role","update");
//            userService.addActionToPrivilege("Role","delete");
//            userService.addActionToPrivilege("Role","get");
//            userService.addActionToPrivilege("Privilege","create");
//            userService.addActionToPrivilege("Privilege","update");
//            userService.addActionToPrivilege("Privilege","delete");
//            userService.addActionToPrivilege("Privilege","get");
//        };
//    }
}
