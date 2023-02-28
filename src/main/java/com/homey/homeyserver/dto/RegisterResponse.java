package com.homey.homeyserver.dto;


import lombok.Builder;
import lombok.Getter;

//주석주석주~
@Builder
@Getter
public class RegisterResponse {
    private String accessToken;
}
