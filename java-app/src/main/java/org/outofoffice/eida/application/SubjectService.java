package org.outofoffice.eida.application;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.domain.Major;
import org.outofoffice.eida.domain.Subject;
import org.outofoffice.eida.domain.SubjectRepository;

import java.util.List;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class SubjectService {

    private final MajorService majorService;
    private final SubjectRepository subjectRepository;

    public void save(String name, String englishName, String majorName){
        Major major = majorService.mustFind(majorName);
        Subject subject = new Subject(name, englishName, major);
        subjectRepository.insert(subject);
    }

    public Subject mustFind(String subjectName) {
        return subjectRepository.joinFind(subjectName, "major").orElseThrow();
    }

    public List<Subject> findAllByMajorName(String majorName) {
        Predicate<Subject> where = subject -> subject.getMajor().getMajorName().equals(majorName);
        return subjectRepository.list(where);
    }
}
