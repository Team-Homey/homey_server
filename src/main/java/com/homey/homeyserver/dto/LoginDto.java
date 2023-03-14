package com.homey.homeyserver.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

public class LoginDto {


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequestTest{
        private String email;
        private String name;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest{
        private String email;
        private String name;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResponse{
        private String accessToken;
        private String refreshToken;
        private boolean isAlreadyRegistered;
    }
}
