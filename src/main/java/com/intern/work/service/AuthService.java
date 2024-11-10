package com.intern.work.service;

import com.intern.work.config.jwt.JwtUtil;
import com.intern.work.domain.User;
import com.intern.work.domain.dto.UserDto;
import com.intern.work.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserDto signUp(UserDto dto) {
        checkDuplicateUser(dto.username());
        String encodePassword = passwordEncoder.encode(dto.password());
        User user = userRepository.save(dto.toEntity(encodePassword));
        return UserDto.from(user);
    }

    private void checkDuplicateUser(String username){
        if(userRepository.findByUsername(username).isPresent()){
            throw new IllegalArgumentException();
        }
    }

    public String login(HttpServletResponse httpResponse, UserDto dto) {
        String password = dto.password();
        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("Wrong password");
        }
        String token = jwtUtil.createToken(user.getUsername(), user.getUserRole());
        String refreshToken = jwtUtil.createRefreshToken(user.getUsername(), user.getUserRole());
        jwtUtil.setRefreshToken(httpResponse, refreshToken);
        return token;
    }
}
