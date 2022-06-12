package com.example.springapp.controller;

import com.example.springapp.application.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MajorController {
    private final MajorService majorService;

    @GetMapping("/major-register")
    public String registerMajor() {
        return "major-register";
    }

}
