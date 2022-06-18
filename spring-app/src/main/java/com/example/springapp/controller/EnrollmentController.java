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

import java.util.*;
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
    public String enroll(@PathVariable String studentCode, EnrollmentForm form) {
        List<EnrollmentForm.Subject> requestedSubjects = new ArrayList<>();
        requestedSubjects.addAll(form.getMajor());
        requestedSubjects.addAll(form.getElectives());

        requestedSubjects
            .forEach(reqSub -> {
                boolean exist = enrollmentService.exist(reqSub.getSubjectName(), studentCode);

                if (reqSub.isEnrolled() && !exist) {
                    enrollmentService.insert(reqSub.getSubjectName(), studentCode);
                } else if (!reqSub.isEnrolled() && exist) {
                    enrollmentService.delete(reqSub.getSubjectName(), studentCode);
                }
            });

        return "redirect:" + ("/enrollment-list/" + studentCode);
    }

    @GetMapping("/enrollment-list/{studentCode}")
    public String enrollListByStudent(@PathVariable String studentCode, Model model) {
        List<Enrollment> enrollments = enrollmentService.findByStudent(studentCode);
        List<SubjectDto> enrolledSubjects = enrollments.stream()
            .map(Enrollment::getSubject)
            .map(SubjectDto::from)
            .collect(Collectors.toList());

        model.addAttribute("subjects", enrolledSubjects);

        return "enrollment-list-by-student";
    }

}
