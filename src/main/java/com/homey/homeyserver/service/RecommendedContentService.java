package com.homey.homeyserver.service;

import com.homey.homeyserver.domain.Family;
import com.homey.homeyserver.domain.RecommendedContent;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.dto.RecommendedContentDto;
import com.homey.homeyserver.repository.FamilyRepository;
import com.homey.homeyserver.repository.RecommendedContentRepository;
import com.homey.homeyserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendedContentService {

    private final RecommendedContentRepository recommendedContentRepository;
    private final UserRepository userRepository;

    private final FamilyRepository familyRepository;

    public void addRecommendedContent(Long familyId, RecommendedContentDto.SaveRequest saveRequest) {
        RecommendedContent recommendedContent = saveRequest.toEntity();
        recommendedContent.setFamily(getFamily(familyId));

        recommendedContentRepository.save(recommendedContent);
    }

    public List<RecommendedContentDto.FindResponse> findRecommendedContents(String email) {
        return getFamilyByUserEmail(email).getRecommendedContents()
                .stream()
                .map(RecommendedContentDto.FindResponse::generateWithEntity)
                .collect(Collectors.toList());
    }

    private Family getFamily(Long familyId) {
        return familyRepository.findById(familyId).orElseThrow(NoSuchElementException::new);
    }

    private Family getFamilyByUserEmail(String email) {
        return getUserByUserEmail(email).getFamily();
    }

    private User getUserByUserEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (!optionalUser.isPresent()) {
            throw new NoSuchElementException("user not found");
        }

        return optionalUser.get();
    }
}
