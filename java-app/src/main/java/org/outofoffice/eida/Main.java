package org.outofoffice.eida;

import org.outofoffice.common.socket.EidaSocketClient;
import org.outofoffice.eida.application.EnrolmentService;
import org.outofoffice.eida.application.MajorService;
import org.outofoffice.eida.application.StudentService;
import org.outofoffice.eida.application.SubjectService;
import org.outofoffice.eida.domain.*;
import org.outofoffice.lib.context.EidaContext;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class Main {

    private static final AtomicLong SEQ = new AtomicLong(1L);
    private static final Scanner scanner = new Scanner(System.in);

    private static final MajorRepository majorRepository;
    private static final MajorService majorService;

    private static final SubjectRepository subjectRepository;
    private static final SubjectService subjectService;

    private static final StudentRepository studentRepository;
    private static final StudentService studentService;

    private static final EnrolmentRepository enrolmentRepository;
    private static final EnrolmentService enrolmentService;

    static {
        EidaContext.init(Main.class, new EidaSocketClient());

        majorRepository = (MajorRepository) EidaContext.getRepository(Major.class);
        majorService = new MajorService(majorRepository);

        subjectRepository = (SubjectRepository) EidaContext.getRepository(Subject.class);
        subjectService = new SubjectService(majorService, subjectRepository);

        studentRepository = (StudentRepository) EidaContext.getRepository(Student.class);
        studentService = new StudentService(majorService, studentRepository);

        enrolmentRepository = (EnrolmentRepository) EidaContext.getRepository(Enrolment.class);
        enrolmentService = new EnrolmentService(subjectService, studentService, enrolmentRepository);
    }


    public static void main(String[] args) {
        while (true) {
            System.out.println("############################");
            System.out.println("수행할 액션을 선택하세요(중로:-1)");
            System.out.println("1. 전공등록, 2. 과목등록, 3. 학생등록, 4. 수강신청");
            System.out.print("=>");
            int i = scanner.nextInt();
            if (i == -1) break;
            scanner.nextLine();

            MAP.get(i).run();
        }
    }


    private static final Runnable onSelectMajorRegister = () -> {
        System.out.print("전공이름을 입력하세요:");
        String majorName = scanner.nextLine();

        System.out.print("영어이름을 입력하세요:");
        String mEnglishName = scanner.nextLine();

        majorService.save(majorName, mEnglishName);

        Major major = majorService.mustFind(majorName);
        System.out.println(major);
    };

    private static final Runnable onSelectSubject = () -> {
        System.out.println("전공을 선택하세요");
        List<Major> majors = majorService.findAll();
        Set<String> majorNames = majors.stream().map(Major::getMajorName).collect(Collectors.toSet());
        System.out.println("=>" +  String.join("\t", majorNames));

        String selectedMajor = "";
        while (!majorNames.contains(selectedMajor)) {
            if (!selectedMajor.equals("")) System.out.println("정신차리세요");
            selectedMajor = scanner.nextLine();
        }

        System.out.print("과목이름을 입력하세요:");
        String subjectName = scanner.nextLine();

        System.out.print("영어이름을 입력하세요:");
        String sEnglishName = scanner.nextLine();

        subjectService.save(subjectName, sEnglishName, selectedMajor);

        Subject subject = subjectService.mustFind(subjectName);
        System.out.println(subject);
    };

    private static final Runnable onSelectStudent = () -> {
        System.out.println("전공을 선택하세요");
        List<Major> majors = majorService.findAll();
        Set<String> majorNames = majors.stream().map(Major::getMajorName).collect(Collectors.toSet());
        System.out.println("=>" + String.join("\t", majorNames));

        String selectedMajor = "";
        while (!majorNames.contains(selectedMajor)) {
            if (!selectedMajor.equals("")) System.out.println("정신차리세요");
            selectedMajor = scanner.nextLine();
        }

        System.out.println("이름을 입력하세요");
        String name = scanner.nextLine();

        System.out.println("생년월일을 입력하세요");
        String birth = scanner.nextLine();

        System.out.println("성별을 입력하세요");
        String gender = scanner.nextLine();

        String studentCode = String.format("%4s%02d%04d", LocalDateTime.now().getYear(), selectedMajor.hashCode() % 100, SEQ.getAndIncrement());

        studentService.save(studentCode, name, birth, gender, selectedMajor);
        Student student = studentService.mustFind(studentCode);
        System.out.println("student : " + student);
    };

    private static final Runnable onEnroll = () -> {
        System.out.println("학생을 선택하세요");
        List<Student> students = studentService.findAll();

        Map<String, Student> studentMap = students.stream().collect(toMap(Student::getStudentCode, identity()));
        List<String> codes = studentMap.keySet().stream().sorted().collect(toList());
        for (int i = 0; i < studentMap.size(); i++) {
            String code = codes.get(i);
            Student student = studentMap.get(code);
            String format = String.format("%d: %s(%s)", i + 1, student.getName(), code);
            System.out.println(format);
        }

        System.out.print("로그인할 학생을 선택하세요:");
        int sel = scanner.nextInt();
        scanner.nextLine();
        String code = codes.get(sel);
        Student student = studentMap.get(code);

        String major = student.getMajor().getMajorName();

        List<Subject> subjectsInMajor = subjectService.findAllByMajorName(major);

        Set<String> subjectNames = subjectsInMajor.stream().map(Subject::getSubjectName).collect(toSet());
        System.out.println("수강할 과목을 선택해주세요.\n" + String.join( ", ", subjectNames));
        String selectedSubject = "";

        while (!subjectNames.contains(selectedSubject)) {
            if (!selectedSubject.equals("")) System.out.println("정신차리세요");
            selectedSubject = scanner.nextLine();
        }

        enrolmentService.insert(selectedSubject, code);
        Enrolment enrolment = enrolmentService.mustFind(code, selectedSubject);
        System.out.println(enrolment);
    };


    private static final Map<Integer, Runnable> MAP = Map.of(
        1, onSelectMajorRegister,
        2, onSelectSubject,
        3, onSelectStudent,
        4, onEnroll
    );

}
