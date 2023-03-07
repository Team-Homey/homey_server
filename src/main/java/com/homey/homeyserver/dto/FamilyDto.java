package com.homey.homeyserver.dto;

import com.homey.homeyserver.domain.Family;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.domain.enums.FamilyRole;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FamilyDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterRequest {
        private String name;

        public Family toEntity() {
            return Family.builder()
                    .name(name).
                    build();
        }
    }

    @Builder
    @Getter
    public static class RegisterResponse {
        private Long id;
        private String name;
        private String hashCode;
        private LocalDateTime regDate;

        public static RegisterResponse generateWithEntity(Family family) {
            return FamilyDto.RegisterResponse.builder()
                    .id(family.getId())
                    .regDate(family.getRegDate())
                    .hashCode(family.getHashCode())
                    .name(family.getName())
                    .build();
        }
    }


    @Builder
    @Getter
    public static class Info {
        private Long id;
        private String name;
        private String hashCode;
        private LocalDateTime regDate;
    }

    @Builder
    @Getter
    public static class findResponse {
        private Long id;
        private String name;
        private String hashCode;
        private LocalDateTime regDate;
        private List<UserDto.UserInfoResponse> users;

        public static findResponse generateWithEntity(Family family, List<UserDto.UserInfoResponse> users) {
            return FamilyDto.findResponse.builder()
                    .hashCode(family.getHashCode())
                    .id(family.getId())
                    .name(family.getName())
                    .users(users)
                    .regDate(family.getRegDate())
                    .build();
        }
    }
}
