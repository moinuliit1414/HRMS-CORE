package com.synesis.hrmis.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synesis.hrmis.domain.Action;
import com.synesis.hrmis.domain.AppUser;
import com.synesis.hrmis.domain.Privilege;
import com.synesis.hrmis.domain.Role;
import com.synesis.hrmis.dto.responseDTO.SuccessResponse;
import com.synesis.hrmis.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('User_get')")
    public ResponseEntity<List<AppUser>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/register")
    //@PreAuthorize("hasAuthority('User_create')")
    public ResponseEntity<?> saveUser(@RequestBody AppUser appUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.ok().body(new SuccessResponse<>(userService.saveUser(appUser)));
    }

    @PostMapping("/role/save")
    @PreAuthorize("hasAuthority('Role_create')")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/privilege/save")
    @PreAuthorize("hasAuthority('Privilege_create')")
    public ResponseEntity<Privilege> savePrivilege(@RequestBody Privilege privilege) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/privilege/save").toUriString());
        return ResponseEntity.created(uri).body(userService.savePrivilege(privilege));
    }

    @PostMapping("/actions/save")
    public ResponseEntity<Action> saveActions(@RequestBody Action action) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/actions/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveActions(action));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRolename());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/privilege/addtorole")
    public ResponseEntity<?> addPrivilegeToRole(@RequestBody PrivilegeToRoleForm form) {
        userService.addPrivilegeToRole(form.getRolename(), form.getPrivilegename());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/privilege/addtoPrivilege")
    public ResponseEntity<?> addActionToPrivilege(@RequestBody ActionsToPrivilegeForm form) {
        userService.addActionToPrivilege(form.getPrivilegename(), form.getActionname());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                AppUser user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("Roles", user.getRoles().stream()
                                .map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String rolename;
}

@Data
class PrivilegeToRoleForm {
    private String rolename;
    private String privilegename;
}

@Data
class ActionsToPrivilegeForm {
    private String privilegename;
    private String actionname;
}
