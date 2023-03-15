package com.homey.homeyserver.dto;

import com.homey.homeyserver.domain.Question;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

public class QuestionDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class SaveRequest {
        private Long familyId;
    }

    @SuperBuilder
    @Getter
    public static class Info {
        private Long id;
        private String content;

        public static Info generateWithEntity(Question question) {
            return Info.builder()
                    .id(question.getId())
                    .content(question.getContent())
                    .build();
        }
    }
    @SuperBuilder
    @Getter
    public static class Details extends Info {
        private List<AnswerDto.Info> answers;

        public static Details generateWithEntity(Question question) {
            return Details.builder()
                    .id(question.getId())
                    .content(question.getContent())
                    .answers(question.getAnswers()
                            .stream()
                            .map(AnswerDto.Info::generateWithEntity)
                            .toList())
                    .build();
        }
    }
}
