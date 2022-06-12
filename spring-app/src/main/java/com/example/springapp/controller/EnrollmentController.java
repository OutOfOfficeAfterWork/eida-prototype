package com.example.springapp.controller;

import com.example.springapp.application.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @GetMapping("/enrollment-list")
    public String getEnrollmentList() {
        return "enrollment-list";
    }
}
