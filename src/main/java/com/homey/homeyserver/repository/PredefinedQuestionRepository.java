package com.homey.homeyserver.repository;


import com.homey.homeyserver.domain.PredefinedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredefinedQuestionRepository extends JpaRepository<PredefinedQuestion, Long> {
}
