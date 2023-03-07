package com.homey.homeyserver.repository;

import com.homey.homeyserver.domain.Question;
import com.homey.homeyserver.domain.RelationshipInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
