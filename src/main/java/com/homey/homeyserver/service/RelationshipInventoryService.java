package com.homey.homeyserver.service;


import com.homey.homeyserver.domain.RelationshipInventory;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.dto.RelationshipInventoryDto;
import com.homey.homeyserver.repository.RelationshipInventoryRepository;
import com.homey.homeyserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RelationshipInventoryService {
        private final UserRepository userRepository;
        private final RelationshipInventoryRepository relationshipInventoryRepository;

    public void save(RelationshipInventoryDto.SaveRequest saveRequest, String email) {
        RelationshipInventory relationshipInventory = RelationshipInventoryDto.SaveRequest.toEntity(saveRequest);
        relationshipInventory.setUser(getUserByEmail(email));
        relationshipInventoryRepository.save(relationshipInventory);
    }

    private User getUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(()
                        -> new NoSuchElementException("User not found"));
    }
}
