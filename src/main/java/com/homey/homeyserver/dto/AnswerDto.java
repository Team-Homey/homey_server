package com.homey.homeyserver.dto;

import com.homey.homeyserver.domain.Answer;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class AnswerDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class SaveRequest {
        private String content;

        public Answer toEntity() {
            return Answer.builder()
                    .content(content)
                    .build();
        }

    }

    @SuperBuilder
    @Getter
    public static class Info {
        protected Long id;
        protected String content;
        protected LocalDateTime regDate;
        protected String name;

        public static Info generateWithEntity(Answer answer) {
            return Info.builder()
                    .content(answer.getContent())
                    .id(answer.getId())
                    .regDate(answer.getRegDate())
                    .name(answer.getUser().getName())
                    .build();
        }
    }

    @SuperBuilder
    @Getter
    public static class Details extends Info {
        private Long userId;
        private Long questionId;
        private List<CommentDto.Info> comments;
        public static Details generateWithEntity(Answer answer) {
            return Details.builder()
                    .content(answer.getContent())
                    .id(answer.getId())
                    .name(answer.getUser().getName())
                    .regDate(answer.getRegDate())
                    .userId(answer.getUser().getId())
                    .questionId(answer.getQuestion().getId())
                    .comments(answer
                            .getComment()
                            .stream()
                            .map(CommentDto.Info::generateWithEntity)
                            .toList())
                    .build();
        }
    }
}
