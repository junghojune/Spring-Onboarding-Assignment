package com.intern.work.controller.response;

import com.intern.work.domain.UserRole;
import com.intern.work.domain.dto.UserDto;
import java.util.Collections;
import java.util.List;
import lombok.Builder;

@Builder
public record SignUpResponseDto(
        String username,
        String nickname,
        List<UserRole> authorities
) {
    public static SignUpResponseDto from(UserDto dto){
        return SignUpResponseDto.builder()
                .username(dto.username())
                .nickname(dto.nickname())
                .authorities(Collections.singletonList(dto.role()))
                .build();
    }
}
