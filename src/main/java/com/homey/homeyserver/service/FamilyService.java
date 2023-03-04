package com.homey.homeyserver.service;


import com.homey.homeyserver.domain.Family;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.dto.FamilyDto;
import com.homey.homeyserver.repository.FamilyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;

    public FamilyDto.RegisterResponse addFamily(FamilyDto.RegisterRequest registerRequest) {
        Family family = registerRequest.toEntity();
        family.setHashCode(generateHashCode());
        Family savedFamily = familyRepository.save(family);

        return FamilyDto.RegisterResponse.builder()
                .id(savedFamily.getId())
                .regDate(savedFamily.getRegDate())
                .hashCode(savedFamily.getHashCode())
                .name(savedFamily.getName())
                .build();
    }

    private String generateHashCode() {
        return UUID.randomUUID().toString();
    }
}
