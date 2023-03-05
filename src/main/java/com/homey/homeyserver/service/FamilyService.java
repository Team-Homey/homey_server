package com.homey.homeyserver.service;


import com.homey.homeyserver.domain.Family;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.dto.FamilyDto;
import com.homey.homeyserver.dto.UserDto;
import com.homey.homeyserver.repository.FamilyRepository;
import com.homey.homeyserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FamilyService {

    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;

    @Transactional
    public FamilyDto.RegisterResponse addFamily(FamilyDto.RegisterRequest registerRequest, String email) {
        Family family = registerRequest.toEntity();
        family.setHashCode(generateHashCode());
        Family savedFamily = familyRepository.save(family);

        User user = userRepository.findByEmail(email).get();
        user.setFamily(savedFamily);
        userRepository.save(user);

        return FamilyDto.RegisterResponse.generateWithEntity(savedFamily);
    }
    public FamilyDto.findResponse findFamilyByUserEmail(String email) {
        Family family = userRepository.findByEmail(email).get().getFamily();

        List<UserDto.UserInfoResponse> users = new ArrayList<>();

        for (User user : family.getUsers()) {
            users.add(UserDto.UserInfoResponse.generateWithEntity(user));
        }

        return FamilyDto.findResponse.generateWithEntity(family, users);
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
