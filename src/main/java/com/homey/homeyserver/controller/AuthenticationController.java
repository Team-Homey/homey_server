package com.homey.homeyserver.controller;


import com.google.api.Http;
import com.homey.homeyserver.dto.LoginDto;
import com.homey.homeyserver.dto.RefreshTokenDto;
import com.homey.homeyserver.dto.RegisterRequest;
import com.homey.homeyserver.dto.RegisterResponse;
import com.homey.homeyserver.service.AuthenticationService;

import com.nimbusds.oauth2.sdk.token.RefreshToken;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/test")
    public LoginDto.LoginResponse loginForDebug(@RequestBody LoginDto.LoginRequestTest loginRequestTest) {
        return authenticationService.loginTest(loginRequestTest);
    }


    //Todo : user 객체를 AuthenticationController에서 저장하는 것이 맞는가..
    @PostMapping
    public LoginDto.LoginResponse login(@RequestBody LoginDto.LoginRequest loginRequest) throws IOException {
        return authenticationService.login(loginRequest);
    }
    @PostMapping("/refresh")
    public ResponseEntity<HashMap<String, String>> refresh(@RequestBody RefreshTokenDto refreshToken) {
        String accessToken = authenticationService.refreshAccessToken(refreshToken.getRefreshToken());
        HashMap<String, String> token = new HashMap<>();
        token.put("accessToken", accessToken);
        HttpStatus httpStatus = HttpStatus.OK;

        if (accessToken == null) {
            httpStatus = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<>(token, new HttpHeaders(), httpStatus);
    }
}
