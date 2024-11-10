package com.intern.work.domain.dto;

import com.intern.work.domain.User;
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

    public static UserDto signUpOf(String username, String password, String nickname) {
        return UserDto.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .role(UserRole.ROLE_USER)
                .build();
    }

    public static UserDto from(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .role(user.getUserRole())
                .createdAt(user.getCreatedAt())
                .createdBy(user.getCreatedBy())
                .updatedAt(user.getUpdatedAt())
                .updatedBy(user.getUpdatedBy())
                .deletedAt(user.getDeletedAt())
                .deletedBy(user.getDeletedBy())
                .build();
    }

    public User toEntity(String encodePassword) {
        return User.of(username, encodePassword, role, nickname);
    }
}
