package com.homey.homeyserver.controller;


import com.homey.homeyserver.dto.AnswerDto;
import com.homey.homeyserver.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity saveAnswer(@RequestBody AnswerDto.SaveRequest saveRequest, Principal principal) {
        answerService.saveAnswer(saveRequest, principal.getName());

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public AnswerDto.Details getAnswer(@PathVariable Long id) {
        return answerService.findAnswer(id);
    }

    @GetMapping("/question/{id}")
    public List<AnswerDto.Info> getAnswersOfQuestion(@PathVariable Long id) {
        return answerService.findAnswersOfQuestion(id);
    }


}
