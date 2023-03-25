package com.homey.homeyserver.controller;

import com.homey.homeyserver.domain.RecommendedContent;
import com.homey.homeyserver.dto.RecommendedContentDto;
import com.homey.homeyserver.service.RecommendedContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommended-content")
@RequiredArgsConstructor
public class RecommendedContentController {
    private final RecommendedContentService recommendedContentService;

    @PostMapping("/family-id/{familyId}")
    public ResponseEntity saveRecommendedContent(@PathVariable Long familyId, @RequestBody RecommendedContentDto.SaveRequest saveRequest) {
        recommendedContentService.addRecommendedContent(familyId, saveRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/family-id/{familyId}")
    public List<RecommendedContentDto.FindResponse> getRecommendedContents(@PathVariable Long familyId) {
        return recommendedContentService.findRecommendedContents(familyId);
    }
}
