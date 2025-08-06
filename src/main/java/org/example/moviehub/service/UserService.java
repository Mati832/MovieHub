package org.example.moviehub.service;

import org.example.moviehub.dto.RegisterRequest;
import org.example.moviehub.enums.RoleType;
import org.example.moviehub.model.Role;
import org.example.moviehub.model.User;
import org.example.moviehub.repository.RoleRepository;
import org.example.moviehub.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service


public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordChecker passwordChecker;


    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, PasswordChecker passwordChecker) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordChecker = passwordChecker;
    }

    public void registerUser(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        if (!passwordChecker.isValid(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password: " + request.getPassword() + " is invalid");
        }

        Set<Role> roles = new HashSet<>();

        if (request.getRoles() == null || request.getRoles().isEmpty()) {
            Role defaultRole = roleRepository.findByRoleType(RoleType.ROLE_USER)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Default role not found"));
            roles.add(defaultRole);
        } else {
            for (RoleType roleType : request.getRoles().stream().map(Role::getRoleType).collect(Collectors.toCollection(HashSet::new))) {
                Role role = roleRepository.findByRoleType(roleType)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role " + roleType + " not found"));
                roles.add(role);
            }
        }
        User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()), roles);
        userRepository.save(user);
    }
}


