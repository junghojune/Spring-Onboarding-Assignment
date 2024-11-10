package com.intern.work.controller.request;

import com.intern.work.domain.dto.UserDto;

public record SignUpRequestDto(
        String username,
        String password,
        String nickname
) {
    public UserDto toDto() {
        return UserDto.signUpOf(username, password, nickname);
    }
}
