package com.example.springapp.controller;

import com.example.springapp.application.EnrollmentService;
import com.example.springapp.application.StudentService;
import com.example.springapp.application.SubjectService;
import com.example.springapp.domain.Enrollment;
import com.example.springapp.domain.Student;
import com.example.springapp.domain.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final SubjectService subjectService;
    private final StudentService studentService;

    @GetMapping("/enrollment-list")
    public String getEnrollmentList(Model model) {
        List<Subject> subjects = subjectService.findAll();
        Map<Subject, List<Enrollment>> enrollmentMap = new HashMap<>();
        subjects.forEach(subject -> enrollmentMap.put(subject, enrollmentService.findBySubject(subject.getSubjectName())));
        model.addAttribute("enrollmentMap", enrollmentMap);
        return "enrollment-list";
    }

    @GetMapping("/enroll/{studentCode}")
    public String enroll(@PathVariable String studentCode, Model model) {
        Student student = studentService.mustFind(studentCode);

        List<Enrollment> enrollments = enrollmentService.findByStudent(studentCode);
        List<Subject> enrolledSubjects = enrollments.stream().map(Enrollment::getSubject).collect(Collectors.toList());

        List<EnrollmentForm.Subject> majors = subjectService.findAllByMajorName(student.getMajor().getMajorName()).stream()
            .map(subject -> new EnrollmentForm.Subject(subject.getSubjectName(), enrolledSubjects.contains(subject)))
            .collect(Collectors.toList());

        List<EnrollmentForm.Subject> electives = subjectService.findAllElectives().stream()
            .map(subject -> new EnrollmentForm.Subject(subject.getSubjectName(), enrolledSubjects.contains(subject)))
            .collect(Collectors.toList());

        model.addAttribute("enrollmentForm", new EnrollmentForm(majors, electives));


        return "enroll";
    }

    @PostMapping("/enroll/{studentCode}")
    public String enroll(@PathVariable String studentCode, EnrollmentForm f) {
        System.out.println(f);
        return "redirect:" + ("/enrollment-list/" + studentCode);
    }
}
