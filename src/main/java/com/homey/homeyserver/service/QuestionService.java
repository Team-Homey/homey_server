package com.homey.homeyserver.service;


import com.homey.homeyserver.domain.Family;
import com.homey.homeyserver.domain.Question;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.dto.QuestionDto;
import com.homey.homeyserver.repository.QuestionRepository;
import com.homey.homeyserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    public void saveQuestion(QuestionDto.SaveRequest registerRequest, String email) {
        Question question = registerRequest.toEntity();

        Family family = getFamilyByUserEmail(email);
        question.setFamily(family);

        questionRepository.save(question);
    }

    public QuestionDto.Details findQuestion(Long id) {
        return QuestionDto.Details
                .generateWithEntity(getQuestionEntityById(id));
    }

    private Question getQuestionEntityById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no such question"));
    }

    public List<QuestionDto.Info> findQuestions(String email) {
        Family family = getFamilyByUserEmail(email);

        List<QuestionDto.Info> questions = new ArrayList<>();

        family.getQuestions().forEach(question ->
                questions.add(QuestionDto.Info.
                        generateWithEntity(question)));

        return questions;
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
