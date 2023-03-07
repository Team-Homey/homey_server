package com.homey.homeyserver.repository;

import com.homey.homeyserver.domain.Answer;
import com.homey.homeyserver.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    public List<Answer> findAllByQuestion(Question question);
}
