package org.outofoffice.eida.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.outofoffice.lib.core.ui.EidaEntity;

@Data
@NoArgsConstructor
public class Enrolment implements EidaEntity<String> {
    private String enrollmentId;
    private Student student;
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
