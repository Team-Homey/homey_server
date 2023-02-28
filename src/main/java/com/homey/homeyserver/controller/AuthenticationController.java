package com.homey.homeyserver.controller;


import com.homey.homeyserver.dto.RegisterRequest;
import com.homey.homeyserver.dto.RegisterResponse;
import com.homey.homeyserver.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public RegisterResponse register(RegisterRequest request) {
        return authenticationService.register(request);
    }
}
