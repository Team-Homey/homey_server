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

        public static UpdateResponse generateWithEntity(User user) {
            return UserDto.UpdateResponse.builder()
                    .id(user.getId())
                    .regDate(user.getRegDate())
                    .build();
        }
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

        public static UserInfoResponse generateWithEntity(User user) {
            return UserDto.UserInfoResponse.builder()
                    .id(user.getId())
                    .address(user.getAddress())
                    .age(user.getAge())
                    .birth(user.getBirth())
                    .email(user.getEmail())
                    .gender(user.getGender())
                    .emotion(user.getEmotion())
                    .familyRole(user.getFamilyRole())
                    .picture(user.getPicture())
                    .regDate(user.getRegDate())
                    .build();
        }
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
