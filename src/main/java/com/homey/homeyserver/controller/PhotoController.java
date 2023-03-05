package com.homey.homeyserver.controller;


import com.homey.homeyserver.dto.PhotoDto;
import com.homey.homeyserver.repository.PhotoRepository;
import com.homey.homeyserver.service.PhotoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photo")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("/{id}")
    public PhotoDto.Info getPhoto(@PathVariable Long id) {
        return photoService.findPhoto(id);
    }
}
