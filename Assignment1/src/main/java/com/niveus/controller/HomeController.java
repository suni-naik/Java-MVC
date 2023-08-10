package com.niveus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    @GetMapping("/")
    public String messageViewer() {
        return "Hello EveryOne!";
    }
    
     @GetMapping("/niveus")
    public String messageForNiveusViewer() {
        return "Hello Niveus! Greetings of the day!!!";
    }
}
