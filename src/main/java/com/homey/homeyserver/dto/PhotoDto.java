package com.homey.homeyserver.dto;

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
}
