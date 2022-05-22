package org.outofoffice.eida.application;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.domain.*;

@RequiredArgsConstructor
public class EnrolmentService {
    private final SubjectService subjectService;
    private final StudentService studentService;
    private final EnrolmentRepository enrolmentRepository;

    public void insert(String subjectName, String studentCode) {
        Student student = studentService.mustFind(studentCode);
        Subject subject = subjectService.mustFind(subjectName);

        String compositeKey = studentCode + "-" + subjectName;
        Enrolment enrolment = new Enrolment(compositeKey, student, subject);
        enrolmentRepository.insert(enrolment);
    }

    public Enrolment mustFind(String studentCode, String subjectName) {
        String compositeKey = studentCode + "-" + subjectName;
        return enrolmentRepository.find(compositeKey).orElseThrow();
    }
}
