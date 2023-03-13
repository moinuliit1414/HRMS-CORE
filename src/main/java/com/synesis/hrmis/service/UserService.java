package com.synesis.hrmis.service;

import com.synesis.hrmis.domain.Action;
import com.synesis.hrmis.domain.AppUser;
import com.synesis.hrmis.domain.Privilege;
import com.synesis.hrmis.domain.Role;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    Privilege savePrivilege(Privilege privilege);
    Action saveActions(Action action);
    void addRoleToUser(String username, String roleName);
    void addPrivilegeToRole(String roleName, String privilegename);
    void addActionToPrivilege(String privilegename,String actionname);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
