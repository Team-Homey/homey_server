package com.homey.homeyserver.controller;


import com.homey.homeyserver.domain.Photo;
import com.homey.homeyserver.dto.PhotoDto;
import com.homey.homeyserver.repository.PhotoRepository;
import com.homey.homeyserver.service.PhotoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
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
@RequestMapping("/photo")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("/{id}")
    public PhotoDto.Info getPhoto(@PathVariable Long id) {
        return photoService.findPhoto(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removePhoto(@PathVariable Long id) {
        photoService.removePhoto(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity modifyPhoto(@PathVariable Long id, @RequestBody Map<String, String> request) {
        photoService.modifyPhoto(id, request.get("title"));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/my-photo")
    public List<PhotoDto.Info> getMyPhoto(Principal principal) {
        return photoService.findPhoto(principal.getName());
    }
    @GetMapping("/family")
    public List<PhotoDto.Info> getFamilyPhoto(Principal principal) {
        return photoService.findFamilyPhoto(principal.getName());
    }

    @PostMapping("/content")
    public PhotoDto.SaveResponse saveUserPhotoContents(@RequestBody PhotoDto.SaveRequest saveRequest, Principal principal) throws IOException {
        return photoService.addUserPhotoContents(saveRequest, principal.getName());
    }

    @PostMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity saveUserPhoto(@RequestPart MultipartFile image, @PathVariable Long id, Principal principal) throws IOException {
        photoService.addUserPhoto(image, id, principal.getName());
        return new ResponseEntity(HttpStatus.OK);
    }


}
