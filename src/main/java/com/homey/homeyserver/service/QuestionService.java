package com.homey.homeyserver.service;


import com.homey.homeyserver.domain.Family;
import com.homey.homeyserver.domain.Question;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.dto.QuestionDto;
import com.homey.homeyserver.repository.FamilyRepository;
import com.homey.homeyserver.repository.QuestionRepository;
import com.homey.homeyserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;
    public void saveQuestion(QuestionDto.SaveRequest registerRequest, String email) {
        Question question = registerRequest.toEntity();

        Family family = getFamilyByUserEmail(email);
        question.setFamily(family);

        questionRepository.save(question);
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
