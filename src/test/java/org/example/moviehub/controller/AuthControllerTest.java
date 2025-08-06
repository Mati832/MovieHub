package org.example.moviehub.controller;

import org.example.moviehub.enums.RoleType;
import org.example.moviehub.model.Role;
import org.example.moviehub.model.User;
import org.example.moviehub.repository.RoleRepository;
import org.example.moviehub.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    String workingPassword="Ppassword123#";
    String workingUsername="newUser";

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        roleRepository.save(new Role(RoleType.ROLE_USER)); // insert default role
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    ResultActions perform(String userJson) throws Exception {
        return mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)
                .with(SecurityMockMvcRequestPostProcessors.csrf()));
    }

    @Test
    void registerUserSuccesfully() throws Exception {
        String userJson = """
                    {
                        "username": "%s",
                        "password": "%s"
                    }
                """.formatted(workingUsername, workingPassword);

        perform(userJson)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("User registered successfully"));

        Optional<User> newuser = userRepository.findByUsername(workingUsername);
        assertTrue(newuser.isPresent());
        assertTrue(passwordEncoder.matches(workingPassword, newuser.get().getPassword()));
        assertTrue(roleRepository.findByRoleType(RoleType.ROLE_USER).isPresent());
        assertTrue(newuser.get().getRoles().stream().map(Role::getRoleType).toList().contains(RoleType.ROLE_USER));
    }

    @Test
    void registerUserWrongPassword() throws Exception {
        String userJson = """
                    {
                        "username": "%s",
                        "password": "%s"
                    }
                """.formatted(workingUsername, "PpAsdadsa21");
        perform(userJson)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.password").value("Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character, and be at least 8 characters long"));
        assertTrue(userRepository.findByUsername(workingUsername).isEmpty());
    }
    @Test
    void registerUserWrongUsername() throws Exception {
        String userJson = """
                    {
                        "username": "%s",
                        "password": "%s"
                    }
                """.formatted("as", workingPassword);
        perform(userJson)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.username").value("Username must be between 3 and 20 characters"));
        assertTrue(userRepository.findByUsername("as").isEmpty());
    }

    @Test
    void registerUserWithRoles() throws Exception {
        roleRepository.save(new Role(RoleType.ROLE_ADMIN));
        String userJson = """
                    {
                        "username": "%s",
                        "password": "%s",
                        "roles": [
                            { "roleType": "ROLE_USER" },
                            { "roleType": "ROLE_ADMIN" }
                          ]
                    }
                """.formatted(workingUsername, workingPassword);
        perform(userJson)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("User registered successfully"));
        Optional<User> user = userRepository.findByUsername(workingUsername);
        assertTrue(user.isPresent());
        List<RoleType> list = user.get().getRoles().stream().map(Role::getRoleType).toList();
        assertTrue(list.contains(RoleType.ROLE_USER)&&list.contains(RoleType.ROLE_ADMIN));
    }
    @Test
    void registerDuplicateUser() throws Exception {
        registerUserSuccesfully();

        String userJson = """
        {
            "username": "%s",
            "password": "%s"
        }
    """.formatted(workingUsername, workingPassword);

        perform(userJson)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Username already exists"));

        assertEquals(1, userRepository.findAll().size());
    }

}
