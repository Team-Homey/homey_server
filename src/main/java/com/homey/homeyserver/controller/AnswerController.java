package com.homey.homeyserver.controller;


import com.homey.homeyserver.dto.AnswerDto;
import com.homey.homeyserver.service.AnswerService;
import com.homey.homeyserver.service.FamilyService;
import lombok.RequiredArgsConstructor;
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
    private final FamilyService familyService;

    @PostMapping("/question/{questionId}")
    public ResponseEntity saveAnswer(@RequestBody AnswerDto.SaveRequest saveRequest,
                                     @PathVariable Long questionId,
                                     Principal principal) {
        answerService.saveAnswer(saveRequest, questionId, principal.getName());
        familyService.updatePoint(principal.getName());

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public AnswerDto.Details getAnswer(@PathVariable Long id) {
        return answerService.findAnswer(id);
    }

    @GetMapping("/question/{questionId}")
    public List<AnswerDto.Info> getAnswersOfQuestion(@PathVariable Long questionId) {
        return answerService.findAnswersOfQuestion(questionId);
    }
}