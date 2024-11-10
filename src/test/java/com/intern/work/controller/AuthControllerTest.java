package com.intern.work.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intern.work.WithCustomMockUser;
import com.intern.work.config.jwt.JwtUtil;
import com.intern.work.controller.request.SignUpRequestDto;
import com.intern.work.domain.User;
import com.intern.work.domain.UserRole;
import com.intern.work.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-secret.yml")
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithCustomMockUser
    public void signUpTest() throws Exception {
        // Create a SignUpRequestDto object to send as the request body
        SignUpRequestDto requestDto = new SignUpRequestDto("testuser", "password123", "testuser@example.com");


        // Perform the POST request to the sign-up endpoint
        mockMvc.perform(post("/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated()) // Expecting 201 Created
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("testuser"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("testuser@example.com"));
    }

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .userId(1L)
                .username("test")
                .password("test")
                .nickname("test")
                .userRole(UserRole.ROLE_USER)
                .build();

        userRepository.save(user);
    }

    @Test
    @WithCustomMockUser
    public void loginTest() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .header("Authorization", "aaaaaaa"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}