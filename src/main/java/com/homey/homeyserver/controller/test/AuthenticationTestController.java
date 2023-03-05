package com.homey.homeyserver.controller.test;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/test")
public class AuthenticationTestController {

    @GetMapping
    public String test(Principal principal) {
        System.out.println("contoller 도달");
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(principal.getName());
        return "authenticated";
    }
}
