package com.homey.homeyserver.service;


import com.homey.homeyserver.domain.Role;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.dto.RegisterRequest;
import com.homey.homeyserver.dto.RegisterResponse;
import com.homey.homeyserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    /*
     * Todo : register, login 로직 작성
     * */
    private final UserRepository userRepository;
    private final JwtService jwtService;

    //login과 로직(메서드) 분리 필요
    public RegisterResponse register(RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .role(Role.USER)
                .build();
        String token = jwtService.generateToken(user);

        if (!userRepository.findByEmail(request.getEmail()).isPresent()) {
            userRepository.save(user);
        }

        return RegisterResponse.builder()
                .accessToken(token)
                .build();
    }
}
