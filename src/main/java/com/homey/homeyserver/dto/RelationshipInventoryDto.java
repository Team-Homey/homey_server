package com.homey.homeyserver.dto;

import com.homey.homeyserver.domain.RelationshipInventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class RelationshipInventoryDto {


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class SaveRequest {
        String result;

        public static RelationshipInventory toEntity(RelationshipInventoryDto.SaveRequest saveRequest) {
            return RelationshipInventory.builder()
                    .result(saveRequest.getResult())
                    .build();
        }
    }


}
