package com.homey.homeyserver.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class AuthenticationTestController {

    @GetMapping
    public String test() {
        System.out.println("contoller 도달");
        return "authenticated";
    }
}
