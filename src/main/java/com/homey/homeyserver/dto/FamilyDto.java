package com.homey.homeyserver.dto;

import com.homey.homeyserver.domain.Family;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.domain.enums.FamilyRole;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        private String code;
        private LocalDateTime regDate;

        public static RegisterResponse generateWithEntity(Family family) {
            return FamilyDto.RegisterResponse.builder()
                    .id(family.getId())
                    .regDate(family.getRegDate())
                    .code(family.getHashCode())
                    .name(family.getName())
                    .build();
        }
    }


    @Builder
    @Getter
    public static class Info {
        private Long id;
        private String name;
        private String code;
        private LocalDateTime regDate;
    }

    @Builder
    @Getter
    public static class findResponse {
        private Long id;
        private String name;
        private String code;
        private LocalDateTime regDate;
        private Integer point;
        private List<UserDto.UserInfoResponse> users;

        public static findResponse generateWithEntity(Family family, List<UserDto.UserInfoResponse> users) {
            return findResponse.builder()
                    .code(family.getHashCode())
                    .id(family.getId())
                    .name(family.getName())
                    .users(users)
                    .point(family.getPoint())
                    .regDate(family.getRegDate())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class FamilyIdsResponse {
        private List<Long> familyIds;

        public static FamilyIdsResponse generateWithEntities(List<Family> families) {
            List<Long> tempFamilyIds = new ArrayList();
            families.forEach((family) -> tempFamilyIds.add(family.getId()));

            return FamilyIdsResponse
                    .builder()
                    .familyIds(tempFamilyIds)
                    .build();
        }
    }
}
