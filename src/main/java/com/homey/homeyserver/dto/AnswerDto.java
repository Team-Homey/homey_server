package com.homey.homeyserver.dto;

import com.homey.homeyserver.domain.Answer;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

public class AnswerDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class SaveRequest {
        private Long questionId;
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
        protected String username;

        public static Info generateWithEntity(Answer answer) {
            return Info.builder()
                    .content(answer.getContent())
                    .id(answer.getId())
                    .regDate(answer.getRegDate())
                    .username(answer.getUser().getName())
                    .build();
        }
    }

    @SuperBuilder
    @Getter
    public static class Details extends Info {
        private Long userId;
        private Long questionId;
        //Todo : Comment API 구현 후 로직에 포함시키기
        public static Details generateWithEntity(Answer answer) {
            return Details.builder()
                    .content(answer.getContent())
                    .id(answer.getId())
                    .regDate(answer.getRegDate())
                    .userId(answer.getUser().getId())
                    .questionId(answer.getQuestion().getId())
                    .build();
        }
    }
}
