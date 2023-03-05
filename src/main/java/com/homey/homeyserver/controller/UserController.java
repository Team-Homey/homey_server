package com.homey.homeyserver.controller;

import com.homey.homeyserver.dto.FamilyDto;
import com.homey.homeyserver.dto.PhotoDto;
import com.homey.homeyserver.dto.UserDto;
import com.homey.homeyserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
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

    @GetMapping("/my-info")
    public UserDto.UserInfoResponse getMyUserInformation(Principal principal) {
        return userService.findUser(principal.getName());
    }

    @PostMapping("/family")
    public ResponseEntity updateUserFamily(@RequestBody Map<String, String> hashCode, @PathVariable Long id) {
        userService.updateUserFamily(hashCode.get("hashCode"), id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
