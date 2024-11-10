package com.intern.work.domain.dto;

import com.intern.work.domain.UserRole;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record UserDto(
        Long userId,
        String username,
        String password,
        UserRole role,
        String nickname,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy,
        LocalDateTime deletedAt,
        String deletedBy
) {

    public static UserDto loginOf(String username, String password) {
        return UserDto.builder()
                .username(username)
                .password(password)
                .build();
    }

}
