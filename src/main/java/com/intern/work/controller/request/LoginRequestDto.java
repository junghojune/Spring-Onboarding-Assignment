package com.intern.work.controller.request;

import com.intern.work.domain.dto.UserDto;

public record LoginRequestDto(
        String username,
        String password
) {
    public UserDto toDto(){
        return UserDto.loginOf(username, password);
    }
}
