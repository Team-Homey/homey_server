package com.homey.homeyserver.dto;

import com.homey.homeyserver.domain.Question;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class QuestionDto {

    @AllArgsConstructor
    @Getter
    @Setter
    public static class SaveRequest {
        private String content;
        private LocalDate date;

        public Question toEntity() {
            return Question.builder()
                    .content(content)
                    .date(date)
                    .build();
        }
    }

}
