package com.homey.homeyserver.controller;


import com.homey.homeyserver.dto.PhotoDto;
import com.homey.homeyserver.repository.PhotoRepository;
import com.homey.homeyserver.service.PhotoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity modifyPhoto(@PathVariable Long id, Map<String, String> request) {
        
    }
}
