package com.intern.work.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/auth")
public class AuthController {

    @PostMapping("/sign-up")
    public String signUp() {
        return "test";
    }

    @PostMapping("/login")
    public String login() {
        return "test";
    }
}
