package com.homey.homeyserver.dto;

import com.homey.homeyserver.domain.Photo;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

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
}
