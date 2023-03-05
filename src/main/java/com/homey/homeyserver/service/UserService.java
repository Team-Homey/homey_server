package com.homey.homeyserver.service;

import com.homey.homeyserver.domain.Family;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.domain.enums.Emotion;
import com.homey.homeyserver.dto.FamilyDto;
import com.homey.homeyserver.dto.UserDto;
import com.homey.homeyserver.repository.FamilyRepository;
import com.homey.homeyserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;

    public UserDto.UserInfoResponse findUser(Long id) {

        User user = getUser(id);

        return UserDto.UserInfoResponse.builder()
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
                .build();
    }

    public UserDto.UpdateResponse modifyUser(UserDto.UpdateRequest updateRequest, Long id) {

        User user = getUser(id);
        user.setAddress(updateRequest.getAddress());
        user.setAge(updateRequest.getAge());
        user.setBirth(updateRequest.getBirth());
        user.setGender(updateRequest.getGender());
        user.setPicture(updateRequest.getPicture());
        user.setFamilyRole(updateRequest.getFamilyRole());

        User savedUser = userRepository.save(user);

        return UserDto.UpdateResponse.builder()
                .id(savedUser.getId())
                .regDate(savedUser.getRegDate())
                .build();
    }

    public UserDto.EmotionUpdateResponse modifyUserEmotion(Emotion emotion, Long id) {
        User user = getUser(id);
        user.setEmotion(emotion);
        User savedUser = userRepository.save(user);

        return UserDto.EmotionUpdateResponse.builder()
                .emotion(savedUser.getEmotion())
                .build();
    }

    private User getUser(Long id) {

        User user = getUser(id);
        return user;
    }

    public void updateUserFamily(String hashCode, Long id) {
        User user = getUser(id);
        Optional<Family> optionalFamily = familyRepository.findByHashCode(hashCode);
        if (!optionalFamily.isPresent()) {
            throw new NoSuchElementException("invalid Family code");
        }

        user.setFamily(optionalFamily.get());
        userRepository.save(user);
    }

    
}
