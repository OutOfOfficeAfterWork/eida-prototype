package org.outofoffice.eida;

import org.outofoffice.common.socket.EidaSocketClient;
import org.outofoffice.eida.application.MajorService;
import org.outofoffice.eida.application.SubjectService;
import org.outofoffice.eida.domain.Major;
import org.outofoffice.eida.domain.MajorRepository;
import org.outofoffice.eida.domain.Subject;
import org.outofoffice.eida.domain.SubjectRepository;
import org.outofoffice.lib.context.EidaContext;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EidaContext.init(Main.class, new EidaSocketClient());

        MajorRepository majorRepository = (MajorRepository) EidaContext.getRepository(Major.class);
        MajorService majorService = new MajorService(majorRepository);

        SubjectRepository subjectRepository = (SubjectRepository) EidaContext.getRepository(Subject.class);
        SubjectService subjectService = new SubjectService(majorService, subjectRepository);


        while (true) {
            System.out.println("############################");
            System.out.println("수행할 액션을 선택하세요(중로:-1)");
            System.out.println("1. 전공등록, 2. 과목등록, 3. 학생등록, 4. 수강신청");
            System.out.print("=>");
            int i = scanner.nextInt();
            if (i == -1) break;
            scanner.nextLine();

            switch (i) {
                case (1):
                    System.out.print("전공이름을 입력하세요:");
                    String majorName = scanner.nextLine();

                    System.out.print("영어이름을 입력하세요:");
                    String mEnglishName = scanner.nextLine();

                    majorService.save(majorName, mEnglishName);

                    Major major = majorService.mustFind(majorName);
                    System.out.println(major);
                    break;

                case (2):
                    System.out.println("전공을 선택하세요");
                    List<Major> majors = majorService.findAll();
                    Set<String> names = majors.stream().map(Major::getMajorName).collect(Collectors.toSet());
                    System.out.println(String.join("\t", names));

                    String mName = "";
                    while (!names.contains(mName)) {
                        if (!mName.equals("")) System.out.println("정신차리세요");
                        mName = scanner.nextLine();
                    }

                    System.out.print("과목이름을 입력하세요:");
                    String name = scanner.nextLine();

                    System.out.print("영어이름을 입력하세요:");
                    String sEnglishName = scanner.nextLine();

                    subjectService.save(name, sEnglishName, mName);

                    Subject subject = subjectService.mustFind(name);
                    System.out.println(subject);
            }
        }
    }
}
