package com.homey.homeyserver.dto;

import com.homey.homeyserver.domain.RecommendedContent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.annotation.Nonnull;

public class RecommendedContentDto {

    @SuperBuilder
    @Getter
    @NoArgsConstructor
    public static class Info {
        private String title;
        private String address;
        private String picture;
        private String url;

    }


    @Getter
    @Setter
    @SuperBuilder
    @AllArgsConstructor
    public static class SaveRequest extends Info {

        public RecommendedContent toEntity() {
            return RecommendedContent.builder()
                    .title(getTitle())
                    .address(getAddress())
                    .picture(getPicture())
                    .url(getUrl())
                    .build();
        }
    }


    @SuperBuilder
    @Getter
    public static class FindResponse extends Info {

        public static FindResponse generateWithEntity(RecommendedContent recommendedContent) {
            return FindResponse.builder()
                    .title(recommendedContent.getTitle())
                    .address(recommendedContent.getAddress())
                    .picture(recommendedContent.getPicture())
                    .url(recommendedContent.getUrl())
                    .build();
        }
    }
}
