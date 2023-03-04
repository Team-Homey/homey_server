package com.homey.homeyserver.controller;


import com.homey.homeyserver.dto.FamilyDto;
import com.homey.homeyserver.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/family")
public class FamilyController {

    private final FamilyService familyService;

    @PostMapping
    public FamilyDto.RegisterResponse saveFamily(@RequestBody FamilyDto.RegisterRequest registerRequest) {
        System.out.println(registerRequest.getName());
        return familyService.addFamily(registerRequest);
    }
}
