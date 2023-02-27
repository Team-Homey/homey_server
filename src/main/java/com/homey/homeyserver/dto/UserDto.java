package com.homey.homeyserver.dto;


import com.homey.homeyserver.domain.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class UserDto {

    @Getter
    @Setter
    @AllArgsConstructor
    //family 연결 요청은 따로 dto로 만들기
    public static class SignUpRequest {
        private String email;
        private Integer age;
        private String gender;
        private LocalDate birth;
        private FamilyRole familyRole;
        private Integer address;
        private String picture;

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .address(address)
                    .age(age)
                    .gender(gender)
                    .birth(birth)
                    .familyRole(familyRole)
                    .picture(picture).build();
        }
    }

    @Builder
    public static class SignUpResponse {
        private Long id;
        private LocalDateTime regDate;
    }
}
