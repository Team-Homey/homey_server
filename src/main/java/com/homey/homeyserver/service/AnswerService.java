package com.homey.homeyserver.service;


import com.homey.homeyserver.domain.Answer;
import com.homey.homeyserver.domain.Question;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.dto.AnswerDto;
import com.homey.homeyserver.repository.AnswerRepository;
import com.homey.homeyserver.repository.QuestionRepository;
import com.homey.homeyserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public AnswerDto.Details findAnswer(Long id) {
        Answer answer = getAnswerById(id);

        return AnswerDto.Details.generateWithEntity(answer);
    }

    public List<AnswerDto.Info> findAnswersOfQuestion(Long questionId) {
        List<AnswerDto.Info> answerInfoList = new ArrayList<>();

        answerRepository
                .findAllByQuestion(getQuestionById(questionId))
                .forEach(answer -> {
                    answerInfoList.add(AnswerDto.Info.generateWithEntity(answer));
        });
        return answerInfoList;
    }

    public void saveAnswer(AnswerDto.SaveRequest saveRequest, Long questionId, String email) {
        Answer answer = saveRequest.toEntity();
        answer.setQuestion(getQuestionById(questionId));
        answer.setUser(getUserByEmail(email));
        answerRepository.save(answer);
    }

    private Answer getAnswerById(Long id) {
        return answerRepository
                .findById(id)
                .orElseThrow(()
                        -> new NoSuchElementException("answer not found"));
    }

    private User getUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(()
                        -> new NoSuchElementException("User not found"));
    }

    private Question getQuestionById(Long questionId) {
        return questionRepository
                .findById(questionId)
                .orElseThrow(()
                        -> new NoSuchElementException("question not found"));
    }
}
