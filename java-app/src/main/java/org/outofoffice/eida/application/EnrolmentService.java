package org.outofoffice.eida.application;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.domain.*;

import java.util.List;

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

    public List<Enrolment> findByStudent(String studentCode) {
        return enrolmentRepository.joinList(e -> e.getStudent().getStudentCode().equals(studentCode), "subject");
    }

    public List<Enrolment> findBySubject(String subjectName) {
        return enrolmentRepository.joinList(e -> e.getSubject().getSubjectName().equals(subjectName), "student");
    }

}
