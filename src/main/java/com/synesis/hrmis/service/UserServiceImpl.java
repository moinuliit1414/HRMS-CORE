package com.synesis.hrmis.service;

import com.synesis.hrmis.domain.Action;
import com.synesis.hrmis.domain.Privilege;
import com.synesis.hrmis.domain.Role;
import com.synesis.hrmis.domain.AppUser;
import com.synesis.hrmis.repository.ActionsRepository;
import com.synesis.hrmis.repository.PrivilegeRepository;
import com.synesis.hrmis.repository.RoleRepository;
import com.synesis.hrmis.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final ActionsRepository actionsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found in database");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role->{
            role.getPrivileges().forEach(privilege -> {
                privilege.getActions().forEach(actions -> {
                    authorities.add(new SimpleGrantedAuthority(privilege.getName()+"_"+actions.getName()));
                });
            });
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
            return user;
        } catch (Exception ex) {
            throw new RuntimeException("User creation failed");
        }
        //return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Privilege savePrivilege(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    @Override
    public Action saveActions(Action action) {
        return actionsRepository.save(action);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public void addPrivilegeToRole(String roleName, String privilegename) {
        Role role = roleRepository.findByName(roleName);
        Privilege privilege = privilegeRepository.findByName(privilegename);
        role.getPrivileges().add(privilege);
    }

    @Override
    public void addActionToPrivilege(String privilegename, String actionname) {
        Privilege privilege = privilegeRepository.findByName(privilegename);
        Action action = actionsRepository.findByName(actionname);
        privilege.getActions().add(action);
    }

    @Override
    public AppUser getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }
}
