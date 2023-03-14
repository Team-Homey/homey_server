package com.homey.homeyserver.service;


import com.homey.homeyserver.domain.Family;
import com.homey.homeyserver.domain.Photo;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.dto.PhotoDto;
import com.homey.homeyserver.repository.PhotoRepository;
import com.homey.homeyserver.repository.UserRepository;
import com.homey.homeyserver.utils.StoragePatchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final StoragePatchUtil storagePatchUtil;
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    public PhotoDto.Info findPhoto(Long id) {
        Photo photo = getPhoto(id);

        return PhotoDto.Info.generateWithEntity(photo);
    }

    public List<PhotoDto.Info> findPhoto(String email) {
        List<Photo> photos = getUser(email).getPhotos();

        List<PhotoDto.Info> myPhotos = new ArrayList<>();
        for (Photo photo : photos) {
            myPhotos.add(PhotoDto.Info.generateWithEntity(photo));
        }
        return myPhotos;
    }

    public List<PhotoDto.Info> findFamilyPhoto(String email) {
        Family family = getUser(email).getFamily();
        List<User> familyMembers = family.getUsers();
        List<PhotoDto.Info> familyPhotos = new ArrayList<>();

        for (User user : familyMembers) {
            for (Photo photo : user.getPhotos()) {
                familyPhotos.add(PhotoDto.Info.generateWithEntity(photo));
            }
        }

        return familyPhotos;
    }

    public void removePhoto(Long id) {
        photoRepository.delete(getPhoto(id));
    }

    public void modifyPhoto(Long id, String title) {
        Photo photo = getPhoto(id);
        photo.setTitle(title);
        photoRepository.save(photo);
    }

    public void addUserPhoto(MultipartFile image, Long photoId, String email) throws IOException {

        String imageUri = storagePatchUtil.uploadFile(image);
        Photo photo = photoRepository.findById(photoId).orElseThrow(() -> new NoSuchElementException("Photo not found"));
        photo.setImage(imageUri);
        photoRepository.save(photo);
    }

    public PhotoDto.SaveResponse addUserPhotoContents(PhotoDto.SaveRequest saveRequest, String email) throws IOException {
        Photo savedPhoto = photoRepository.save(Photo.builder()
                .user(getUser(email))
                .title(saveRequest.getTitle())
                .build());

        return PhotoDto.SaveResponse.builder()
                .id(savedPhoto.getId())
                .image(savedPhoto.getImage())
                .regDate(savedPhoto.getRegDate())
                .title(saveRequest.getTitle())
                .build();
    }
    private Photo getPhoto(Long id) {
        Optional<Photo> optionalPhoto = photoRepository.findById(id);

        if (!optionalPhoto.isPresent()) {
            throw new NoSuchElementException("photo not exist");
        }

        return optionalPhoto.get();
    }

    private User getUser(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (!optionalUser.isPresent()) {
            throw new NoSuchElementException("user not found");
        }

        return optionalUser.get();
    }
}
