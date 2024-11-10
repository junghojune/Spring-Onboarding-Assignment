package com.intern.work.controller.response;

public record LoginResponseDto(
        String token
) {
    public static LoginResponseDto from(String token) {
        return new LoginResponseDto(token);
    }
}
