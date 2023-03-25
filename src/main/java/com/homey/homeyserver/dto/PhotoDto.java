package com.homey.homeyserver.dto;

import com.homey.homeyserver.domain.Photo;
import com.homey.homeyserver.domain.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PhotoDto {


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveRequest {
        private String title;
    }

    @Builder
    @Getter
    public static class SaveResponse {
        private Long id;
        private String image;
        private String title;
        private LocalDateTime regDate;
    }
    @Builder
    @Getter
    public static class Info {
        private Long id;
        private String image;
        private String title;
        private LocalDateTime regDate;

        public static Info generateWithEntity(Photo photo) {
            return Info.builder()
                    .id(photo.getId())
                    .regDate(photo.getRegDate())
                    .image(photo.getImage())
                    .title(photo.getTitle())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class PhotoUrlResponse {
        private String address;
        private List<String> images;

        public static PhotoUrlResponse generateWithEntities(List<Photo> photos, User user) {
            return PhotoUrlResponse.builder()
                    .address(user.getAddress())
                    .images(photos
                            .stream()
                            .map(Photo::getImage)
                            .collect(Collectors.toList()))
                    .build();
        }
    }
}
