package com.homey.homeyserver.controller;


import com.homey.homeyserver.dto.CommentDto;
import com.homey.homeyserver.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentSerivce;

    @PostMapping("/answer/{answerId}")
    public ResponseEntity saveComment(@RequestBody CommentDto.SaveRequest saveRequest,
                                      @PathVariable Long answerId,
                                      Principal principal) {
        commentSerivce.addComment(saveRequest, answerId, principal.getName());

        return new ResponseEntity(HttpStatus.OK);
    }
}
