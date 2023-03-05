package com.homey.homeyserver.service;


import com.homey.homeyserver.domain.Photo;
import com.homey.homeyserver.dto.PhotoDto;
import com.homey.homeyserver.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    public PhotoDto.Info findPhoto(Long id) {
        Photo photo = getPhoto(id);

        return PhotoDto.Info.generateWithEntity(photo);
    }

    public void removePhoto(Long id) {
        photoRepository.delete(getPhoto(id));
    }

    private Photo getPhoto(Long id) {
        Optional<Photo> optionalPhoto = photoRepository.findById(id);

        if (!optionalPhoto.isPresent()) {
            throw new NoSuchElementException("photo not exist");
        }

        return optionalPhoto.get();
    }
}
