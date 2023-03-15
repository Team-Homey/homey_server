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


//    // Todo : predefinedQuestion으로부터 가져오기
//    @PostMapping
//    public ResponseEntity saveQuestion(@RequestBody QuestionDto.SaveRequest registerRequest, Principal principal) {
//        questionService.saveQuestion(registerRequest, principal.getName());
//        return new ResponseEntity(HttpStatus.OK);
//    }
    @PostMapping("/family")
    public ResponseEntity addQuestion(@RequestBody QuestionDto.SaveRequest registerRequest) {
        questionService.addQuestion(registerRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/family")
    public List<QuestionDto.Info> getQuestionsOfFamily(Principal principal) {
        return questionService.findQuestions(principal.getName());
    }

    @GetMapping("/{id}")
    public QuestionDto.Details getQuestion(@PathVariable Long id) {
        return questionService.findQuestion(id);
    }
}
