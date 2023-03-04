package com.homey.homeyserver.service;


import com.homey.homeyserver.domain.Role;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.dto.LoginDto;
import com.homey.homeyserver.dto.RegisterRequest;
import com.homey.homeyserver.dto.RegisterResponse;
import com.homey.homeyserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    /*
     * register 로직
     * */
    private final UserRepository userRepository;
    private final JwtService jwtService;

    //Todo : refreshToken 발급 로직 추가
    public LoginDto.LoginResponse login(LoginDto.LoginRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .role(Role.USER)
                .build();

        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        LoginDto.LoginResponse loginResponse = LoginDto.LoginResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .isAlreadyRegistered(true)
                .build();

        if (!userRepository.findByEmail(request.getEmail()).isPresent()) {
            loginResponse.setAlreadyRegistered(false);
            userRepository.save(user);
        }

        return loginResponse;
    }


    //refreshToken의 유효성 검증 후 accessToken을 발급해 반환한다.
    // Todo : 유효성 검증 기능 분리. JwtService 단으로 옮길 부분이 많다. 비즈니스 로직만 남기고 메서드명 바꾸기
    public String refreshAccessToken(String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);
        Optional<User> optionalUser = userRepository.findByEmail(username);

        if(!optionalUser.isPresent()) {
            return null;
        }

        UserDetails userDetails = optionalUser.get();

        if (!jwtService.isTokenValid(refreshToken, userDetails)) {
            return null;
        }

        return jwtService.generateToken(userDetails);
    }

}
