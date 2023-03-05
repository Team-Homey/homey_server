package com.homey.homeyserver.dto;


import com.homey.homeyserver.domain.*;
import com.homey.homeyserver.domain.enums.Emotion;
import com.homey.homeyserver.domain.enums.FamilyRole;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class UserDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class UpdateRequest {
        private Integer age;
        private String gender;
        private String address;
        private String picture;
        private LocalDate birth;
        private FamilyRole familyRole;

        //필요없음.
        public User toEntity() {
            return User.builder()
                    .address(address)
                    .age(age)
                    .gender(gender)
                    .birth(birth)
                    .familyRole(familyRole)
                    .picture(picture)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class UpdateResponse {
        private Long id;
        private LocalDateTime regDate;
    }

    @Builder
    @Getter
    public static class UserInfoResponse {
        private Long id;
        private String email;
        private Integer age;
        private String gender;
        private String address;
        private String picture;
        private LocalDateTime regDate;
        private LocalDate birth;
        private FamilyRole familyRole;
        private Emotion emotion;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EmotionUpdateRequest {
        private Emotion emotion;

    }

    @Builder
    @Getter
    public static class EmotionUpdateResponse {
        private Emotion emotion;
    }
}
