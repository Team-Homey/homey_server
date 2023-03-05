package com.homey.homeyserver.controller;


import com.homey.homeyserver.dto.QuestionDto;
import com.homey.homeyserver.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity saveQuestion(@RequestBody QuestionDto.SaveRequest registerRequest, Principal principal) {
        questionService.saveQuestion(registerRequest, principal.getName());
        return new ResponseEntity(HttpStatus.OK);
    }
}
