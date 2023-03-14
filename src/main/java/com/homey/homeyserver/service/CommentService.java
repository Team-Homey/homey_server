package com.homey.homeyserver.service;

import com.homey.homeyserver.domain.Answer;
import com.homey.homeyserver.domain.Comment;
import com.homey.homeyserver.domain.User;
import com.homey.homeyserver.dto.CommentDto;
import com.homey.homeyserver.repository.AnswerRepository;
import com.homey.homeyserver.repository.CommentRepository;
import com.homey.homeyserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    public void addComment(CommentDto.SaveRequest saveRequest, Long answerId, String email) {
        commentRepository.save(Comment.builder()
                .content(saveRequest.getContent())
                .answer(getAnswerById(answerId))
                .user(getUserByEmail(email))
                .build());
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
}
