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
    }
}
