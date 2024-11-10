package com.intern.work.controller;

import com.intern.work.controller.request.LoginRequestDto;
import com.intern.work.controller.request.SignUpRequestDto;
import com.intern.work.controller.response.LoginResponseDto;
import com.intern.work.controller.response.SignUpResponseDto;
import com.intern.work.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto request) {
        SignUpResponseDto response = SignUpResponseDto.from(authService.signUp(request.toDto()));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(HttpServletResponse httpResponse, @RequestBody LoginRequestDto loginRequest) {
        LoginResponseDto response = LoginResponseDto.from(authService.login(httpResponse, loginRequest.toDto()));
        return ResponseEntity.ok(response);
    }
}
