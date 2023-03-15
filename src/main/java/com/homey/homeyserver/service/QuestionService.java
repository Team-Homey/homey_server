package com.homey.homeyserver.service;


import com.homey.homeyserver.domain.Family;
import com.homey.homeyserver.domain.PredefinedQuestion;
import com.homey.homeyserver.domain.Question;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.dto.QuestionDto;
import com.homey.homeyserver.repository.FamilyRepository;
import com.homey.homeyserver.repository.PredefinedQuestionRepository;
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
    private final FamilyRepository familyRepository;
    private final PredefinedQuestionRepository predefinedQuestionRepository;

    public QuestionDto.Details findQuestion(Long id) {
        return QuestionDto.Details
                .generateWithEntity(getQuestionEntityById(id));
    }

    public List<QuestionDto.Info> findQuestions(String email) {
        Family family = getFamilyByUserEmail(email);

        List<QuestionDto.Info> questions = new ArrayList<>();

        family.getQuestions().forEach(question ->
                questions.add(QuestionDto.Info.
                        generateWithEntity(question)));

        return questions;
    }

    public void addQuestion(QuestionDto.SaveRequest registerRequest) {
        Family family = familyRepository
                .findById(registerRequest.getFamilyId()).orElseThrow(()
                        -> new NoSuchElementException("family not found"));

        Long questionIdOfToday = (long) (family.getQuestions().size() + 1);

        PredefinedQuestion questionOfToday = predefinedQuestionRepository.findById(questionIdOfToday).orElseThrow(()
                -> new NoSuchElementException("predefinedQuestion not found"));

        questionRepository.save(Question.builder()
                .content(questionOfToday.getContent())
                .family(family)
                .build());

    }

    private Question getQuestionEntityById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no such question"));
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
