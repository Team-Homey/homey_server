package com.homey.homeyserver.service;

import com.homey.homeyserver.domain.Family;
import com.homey.homeyserver.domain.Photo;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.domain.enums.Emotion;
import com.homey.homeyserver.dto.FamilyDto;
import com.homey.homeyserver.dto.PhotoDto;
import com.homey.homeyserver.dto.UserDto;
import com.homey.homeyserver.repository.FamilyRepository;
import com.homey.homeyserver.repository.PhotoRepository;
import com.homey.homeyserver.repository.UserRepository;
import com.homey.homeyserver.utils.StoragePatchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final StoragePatchUtil storagePatchUtil;
    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;
    private final PhotoRepository photoRepository;
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
        Optional<User> optionalUser = userRepository.findById(id);
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

    public FamilyDto.Info findUserFamily(Long id) {
        Family family = getUser(id).getFamily();

        return FamilyDto.Info.builder()
                .hashCode(family.getHashCode())
                .id(family.getId())
                .name(family.getName())
                .regDate(family.getRegDate())
                .build();
    }

    public PhotoDto.SaveResponse addUserPhoto(PhotoDto.SaveRequest saveRequest, MultipartFile image, Long id) throws IOException {
        /* Todo -
            1. multipartFile gcs로 업로드
            2. uri, title, user 정보 담아서 Photo Entity 생성, db에 저장
            3. 결과 dto에 담아 반환
        * */

        String imageUri = storagePatchUtil.uploadFile(image);
        Photo savedPhoto = photoRepository.save(Photo.builder()
                .user(getUser(id))
                .image(imageUri)
                .title(saveRequest.getTitle())
                .build());

        return PhotoDto.SaveResponse.builder()
                .id(savedPhoto.getId())
                .image(savedPhoto.getImage())
                .regDate(savedPhoto.getRegDate())
                .title(saveRequest.getTitle())
                .build();
    }
}
