package org.example.moviehub.controller;

import org.example.moviehub.enums.RoleType;
import org.example.moviehub.model.Role;
import org.example.moviehub.model.User;
import org.example.moviehub.repository.RoleRepository;
import org.example.moviehub.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User requestUser) {
        RoleType userRole = RoleType.ROLE_USER;
        if (userRepository.findByUsername(requestUser.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        Set<Role> roles = new HashSet<>();
        if (requestUser.getRoles() == null || requestUser.getRoles().isEmpty()) {
            Role role = roleRepository.findByRoleType(userRole).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Default role " + userRole + " not found in Database"));
            roles.add(role);
        } else {
            for (Role role : requestUser.getRoles()) {
                Role roleToAdd = roleRepository.findByRoleType(role.getRoleType()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role: " + role + " not found in Database"));
                roles.add(roleToAdd);
            }
        }
        User user = new User(requestUser.getUsername(), passwordEncoder.encode(requestUser.getPassword()), roles);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
