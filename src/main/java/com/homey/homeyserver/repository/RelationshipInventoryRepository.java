package com.homey.homeyserver.repository;

import com.homey.homeyserver.domain.RelationshipInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipInventoryRepository extends JpaRepository<RelationshipInventory, Long> {
}
