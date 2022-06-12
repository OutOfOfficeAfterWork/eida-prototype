package com.example.springapp.controller;

import com.example.springapp.application.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final StudentService studentService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        SignUpForm form = new SignUpForm();
        model.addAttribute("signUpForm", form);
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(SignUpForm f) {
        studentService.save(f.getStudentCode(), f.getName(), f.getBirthDate(), f.getGender(), f.getMajorName());
        return "redirect:" + ("/");
    }
}
