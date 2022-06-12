package com.example.springapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SubjectController {

    @GetMapping("/subject-register")
    public String registerSubject() {
        return "subject-register";
    }

}
