package com.homey.homeyserver.dto;

import com.homey.homeyserver.domain.Question;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

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

    @SuperBuilder
    @Getter
    public static class Info {
        private Long id;
        private String content;
        private LocalDate date;

        public static Info generateWithEntity(Question question) {
            return Info.builder()
                    .id(question.getId())
                    .content(question.getContent())
                    .date(question.getDate())
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
                    .date(question.getDate())
                    .answers(question.getAnswers()
                            .stream()
                            .map(AnswerDto.Info::generateWithEntity)
                            .toList())
                    .build();
        }
    }
}
