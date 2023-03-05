package com.homey.homeyserver.service;


import com.homey.homeyserver.domain.Family;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.dto.FamilyDto;
import com.homey.homeyserver.dto.UserDto;
import com.homey.homeyserver.repository.FamilyRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    public FamilyDto.findResponse findFamily(Long id) {
        Family family = getFamily(id);

        List<UserDto.UserInfoResponse> users = new ArrayList<>();

        for (User user : family.getUsers()) {
            users.add(UserDto.UserInfoResponse.builder()
                    .id(user.getId())
                    .address(user.getAddress())
                    .age(user.getAge())
                    .birth(user.getBirth())
                    .email(user.getEmail())
                    .gender(user.getGender())
                    .emotion(user.getEmotion())
                    .familyRole(user.getFamilyRole())
                    .picture(user.getPicture())
                    .regDate(user.getRegDate())
                    .build());
        }

        return FamilyDto.findResponse.builder()
                .hashCode(family.getHashCode())
                .id(family.getId())
                .name(family.getName())
                .users(users)
                .regDate(family.getRegDate())
                .build();
    }

    private String generateHashCode() {
        return UUID.randomUUID().toString();
    }

    private Family getFamily(Long id) {
        Optional<Family> optionalFamily = familyRepository.findById(id);
        if (!optionalFamily.isPresent()) {
            throw new NoSuchElementException("family not found");
        }
        return optionalFamily.get();
    }
}
