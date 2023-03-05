package com.homey.homeyserver.controller;

import com.homey.homeyserver.dto.FamilyDto;
import com.homey.homeyserver.dto.UserDto;
import com.homey.homeyserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto.UserInfoResponse getUser(@PathVariable Long id) {
        return userService.findUser(id);
    }

    @PostMapping("/{id}")
    public UserDto.UpdateResponse updateUser(@RequestBody UserDto.UpdateRequest updateRequest, @PathVariable Long id) {
        return userService.modifyUser(updateRequest, id);
    }

    @PostMapping("/{id}/emotion")
    public UserDto.EmotionUpdateResponse updateUserEmotion(@RequestBody UserDto.EmotionUpdateRequest emotion, @PathVariable Long id) {
        return userService.modifyUserEmotion(emotion.getEmotion(), id);
    }

    @PostMapping("/{id}/family")
    public ResponseEntity updateUserFamily(@RequestBody Map<String, String> hashCode, @PathVariable Long id) {
        userService.updateUserFamily(hashCode.get("hashCode"), id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}/family")
    public FamilyDto.Info getUserFamily(@PathVariable Long id) {
        return userService.findUserFamily(id);
    }
}
