package com.homey.homeyserver.controller;

import com.homey.homeyserver.dto.FamilyDto;
import com.homey.homeyserver.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/family")
public class FamilyController {

    private final FamilyService familyService;

    @PostMapping
    public FamilyDto.RegisterResponse saveFamily(@RequestBody FamilyDto.RegisterRequest registerRequest, Principal principal) {
        return familyService.addFamily(registerRequest, principal.getName());
    }

    @GetMapping("/my-family")
    public FamilyDto.findResponse getMyFamily(Principal principal) {
        return familyService.findFamilyByUserEmail(principal.getName());
    }
    @GetMapping("/ids")
    public FamilyDto.FamilyIdsResponse getFamilyIds() {
        return familyService.findFamilyIds();
    }
}
