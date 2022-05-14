package org.outofoffice.eida.domain;

import lombok.Getter;
import org.outofoffice.lib.core.ui.EidaEntity;

public class Enrolment implements EidaEntity<String> {
    private String enrollmentId;
    @Getter
    private Student student;
    @Getter
    private Subject subject;

    public Enrolment (Student student, Subject subject) {
        this.student = student;
        this.subject = subject;
    }

    @Override
    public String getId() {
        return enrollmentId;
    }

    @Override
    public void setId(String s) {
        enrollmentId = s;
    }
}
