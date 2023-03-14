package com.homey.homeyserver.controller;


import com.homey.homeyserver.domain.Question;
import com.homey.homeyserver.dto.QuestionDto;
import com.homey.homeyserver.service.QuestionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

//    @GetMapping
//    public List<QuestionDto.Info> getQuestions(Principal principal) {
//        return questionService.findQuestions(principal.getName());
//    }

    //Todo : particular GET endpoint
    @GetMapping("/{id}")
    public QuestionDto.Details getQuestion(@PathVariable Long id) {
        return questionService.findQuestion(id);
    }
}
