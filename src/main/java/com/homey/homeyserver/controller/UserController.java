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

    //Family Controller에서 처리해야하는 로직 아닌가?
    @PostMapping("/{id}/family")
    public ResponseEntity updateUserFamily(@RequestBody Map<String, String> hashCode, @PathVariable Long id) {
        userService.updateUserFamily(hashCode.get("hashCode"), id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}/family")
    public FamilyDto.Info getUserFamily(@PathVariable Long id) {
        return userService.findUserFamily(id);
    }

    @PostMapping(value = "/{id}/photo", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public PhotoDto.SaveResponse saveUserPhoto(@RequestPart PhotoDto.SaveRequest saveRequest,
                                               @RequestPart MultipartFile image,
                                               @PathVariable Long id) throws IOException {
        System.out.println(saveRequest.getTitle());
        System.out.println(image.getContentType());
        return userService.addUserPhoto(saveRequest, image, id);
    }
}
