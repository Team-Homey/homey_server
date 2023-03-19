package com.homey.homeyserver.service;

import com.homey.homeyserver.domain.Family;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.domain.enums.Emotion;
import com.homey.homeyserver.dto.UserDto;
import com.homey.homeyserver.repository.FamilyRepository;
import com.homey.homeyserver.repository.UserRepository;
import com.homey.homeyserver.utils.StoragePatchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;
    private final StoragePatchUtil storagePatchUtil;
    public UserDto.UserInfoResponse findUser(Long id) {

        User user = getUser(id);

        return UserDto.UserInfoResponse.generateWithEntity(user);
    }
    public UserDto.UserInfoResponse findUser(String email) {

        User user = getUser(email);

        return UserDto.UserInfoResponse.generateWithEntity(user);
    }
    public UserDto.UpdateResponse modifyUser(UserDto.UpdateRequest updateRequest, String email) {

        User user = getUser(email);
        user.setAddress(updateRequest.getAddress());
        user.setAge(updateRequest.getAge());
        user.setBirth(updateRequest.getBirth());
        user.setGender(updateRequest.getGender());
        user.setName(updateRequest.getName());
        user.setFamilyRole(updateRequest.getFamilyRole());

        User savedUser = userRepository.save(user);

        return UserDto.UpdateResponse.generateWithEntity(savedUser);
    }

    public UserDto.EmotionUpdateResponse modifyUserEmotion(Emotion emotion, String email) {
        User user = getUser(email);
        user.setEmotion(emotion);
        User savedUser = userRepository.save(user);

        return UserDto.EmotionUpdateResponse.builder()
                .emotion(savedUser.getEmotion())
                .build();
    }

    private User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new NoSuchElementException("User Not Found");
        }
        User user = optionalUser.get();
        return user;
    }
    private User getUser(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            throw new NoSuchElementException("User Not Found");
        }
        User user = optionalUser.get();
        return user;
    }
    public void updateUserFamily(String hashCode, Long id) {
        User user = getUser(id);
        Optional<Family> optionalFamily = familyRepository.findByHashCode(hashCode);


        user.setFamily(optionalFamily.get());
        userRepository.save(user);
    }

    public void savePicture(MultipartFile image, String email) throws IOException {
        String imageUri = storagePatchUtil.uploadFile(image);
        User user = getUser(email);
        user.setPicture(imageUri);
        userRepository.save(user);
    }

    public UserDto.EmotionUpdateResponse getUserEmotion(String email) {
        return UserDto.EmotionUpdateResponse
                .builder()
                .emotion(getUser(email)
                        .getEmotion())
                .build();
    }
}
