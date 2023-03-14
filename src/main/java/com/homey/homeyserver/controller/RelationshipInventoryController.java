package com.homey.homeyserver.controller;


import com.homey.homeyserver.domain.RelationshipInventory;
import com.homey.homeyserver.dto.RelationshipInventoryDto;
import com.homey.homeyserver.service.RelationshipInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/relationshipinventory")
public class RelationshipInventoryController {

    private final RelationshipInventoryService relationshipInventoryService;

    @PostMapping
    public ResponseEntity saveRelationshipInventory(RelationshipInventoryDto.SaveRequest saveRequest, Principal principal) {
        relationshipInventoryService.save(saveRequest, principal.getName());

        return new ResponseEntity(HttpStatus.OK);
    }
}
