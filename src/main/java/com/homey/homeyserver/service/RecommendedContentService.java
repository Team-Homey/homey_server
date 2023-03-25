package com.homey.homeyserver.service;

import com.homey.homeyserver.domain.Family;
import com.homey.homeyserver.domain.RecommendedContent;
import com.homey.homeyserver.dto.RecommendedContentDto;
import com.homey.homeyserver.repository.FamilyRepository;
import com.homey.homeyserver.repository.RecommendedContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendedContentService {

    private final RecommendedContentRepository recommendedContentRepository;
    private final FamilyRepository familyRepository;

    public void addRecommendedContent(Long familyId, RecommendedContentDto.SaveRequest saveRequest) {
        RecommendedContent recommendedContent = saveRequest.toEntity();
        recommendedContent.setFamily(getFamily(familyId));

        recommendedContentRepository.save(recommendedContent);
    }

    public List<RecommendedContentDto.FindResponse> findRecommendedContents(Long familyId) {
        return getFamily(familyId).getRecommendedContents()
                .stream()
                .map(RecommendedContentDto.FindResponse::generateWithEntity)
                .collect(Collectors.toList());
    }

    private Family getFamily(Long familyId) {
        return familyRepository.findById(familyId).orElseThrow(NoSuchElementException::new);
    }
}
