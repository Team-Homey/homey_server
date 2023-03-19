package com.homey.homeyserver.controller;

import com.homey.homeyserver.dto.UserDto;
import com.homey.homeyserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping
    public UserDto.UpdateResponse updateUser(@RequestBody UserDto.UpdateRequest updateRequest, Principal principal) {
        return userService.modifyUser(updateRequest, principal.getName());
    }

    @PostMapping("/emotion")
    public UserDto.EmotionUpdateResponse updateUserEmotion(@RequestBody UserDto.EmotionUpdateRequest emotion, Principal principal) {
        return userService.modifyUserEmotion(emotion.getEmotion(), principal.getName());
    }

    @GetMapping("/{id}")
    public UserDto.UserInfoResponse getUser(@PathVariable Long id) {
        return userService.findUser(id);
    }


    @GetMapping("/my-emotion")
    public UserDto.EmotionUpdateResponse getMyEmotion(Principal principal) {
        return userService.getUserEmotion(principal.getName());
    }


    // Todo :가족 모두의 emotion 반환
//    @GetMapping("/family-emotions")
//    public List<UserDto.EmotionUpdateResponse> getFamilyEmotions(Principal principal) {
//
//    }

    @GetMapping("/my-info")
    public UserDto.UserInfoResponse getMyUserInformation(Principal principal) {
        return userService.findUser(principal.getName());
    }


    @PostMapping(value =  "/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity savePicture(@RequestPart MultipartFile image, Principal principal) throws IOException {
        userService.savePicture(image, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/family")
    public ResponseEntity updateUserFamily(@RequestBody Map<String, String> hashCode, @PathVariable Long id) {
        userService.updateUserFamily(hashCode.get("hashCode"), id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
