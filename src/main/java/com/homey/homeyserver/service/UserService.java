package com.homey.homeyserver.service;


import com.google.api.gax.rpc.NotFoundException;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.domain.enums.Emotion;
import com.homey.homeyserver.dto.UserDto;
import com.homey.homeyserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto.UserInfoResponse findUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new NoSuchElementException("User Not Found.");
        }
        User user = optionalUser.get();

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
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new NoSuchElementException("User Not Found.");
        }
        User user = optionalUser.get();
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
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new NoSuchElementException("User Not Found.");
        }
        User user = optionalUser.get();
        user.setEmotion(emotion);
        User savedUser = userRepository.save(user);

        return UserDto.EmotionUpdateResponse.builder()
                .emotion(savedUser.getEmotion())
                .build();
    }
}
