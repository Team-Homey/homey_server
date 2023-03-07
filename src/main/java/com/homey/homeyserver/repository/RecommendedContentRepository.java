package com.homey.homeyserver.repository;

import com.homey.homeyserver.domain.RecommendedContent;
import com.homey.homeyserver.domain.RelationshipInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendedContentRepository extends JpaRepository<RecommendedContent, Long> {
}
