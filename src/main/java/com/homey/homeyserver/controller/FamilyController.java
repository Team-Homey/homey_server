package com.homey.homeyserver.controller;


import com.homey.homeyserver.dto.FamilyDto;
import com.homey.homeyserver.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/family")
public class FamilyController {

    private final FamilyService familyService;

    @PostMapping
    public FamilyDto.RegisterResponse saveFamily(@RequestBody FamilyDto.RegisterRequest registerRequest) {
        return familyService.addFamily(registerRequest);
    }

    @GetMapping("/{id}")
    public FamilyDto.findResponse getFamily(@PathVariable Long id) {
        return familyService.findFamily(id);
    }
}
