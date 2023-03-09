package com.homey.homeyserver.controller.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingPong {

    @GetMapping
    public String ping() {
        System.out.println("pingpong");
        return "pong";
    }

}

