package com.example.springapp.controller;

import com.example.springapp.application.EnrollmentService;
import com.example.springapp.application.SubjectService;
import com.example.springapp.domain.Enrollment;
import com.example.springapp.domain.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final SubjectService subjectService;

    @GetMapping("/enrollment-list")
    public String getEnrollmentList(Model model) {
        List<Subject> subjects = subjectService.findAll();
        Map<Subject, List<Enrollment>> enrollmentMap = new HashMap<>();
        subjects.forEach(subject -> enrollmentMap.put(subject, enrollmentService.findBySubject(subject.getSubjectName())));
        model.addAttribute("enrollmentMap", enrollmentMap);
        return "enrollment-list";
    }
}
