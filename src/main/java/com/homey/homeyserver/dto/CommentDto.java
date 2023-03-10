package com.homey.homeyserver.dto;

import com.homey.homeyserver.domain.Comment;
import lombok.*;

import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    @Builder
    public static class Info {
        private String content;
        private String username;
        private LocalDateTime regDate;

        public static Info generateWithEntity(Comment comment) {
            return Info.builder()
                    .content(comment.getContent())
                    .regDate(comment.getRegDate())
                    .username(comment.getUser().getName())
                    .build();
        }
    }

    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
    public static class SaveRequest {
        private String content;

        public Comment toEntity() {
            return Comment.builder()
                    .content(content)
                    .build();
        }
    }
}
