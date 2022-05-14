package org.outofoffice.eida.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.outofoffice.lib.core.ui.EidaEntity;

@AllArgsConstructor
public class Student implements EidaEntity<String> {
    private String studentCode;
    private String name;
    private String birthDate;
    private String gender;
    @Getter
    private Major major;

    @Override
    public String getId() {
        return studentCode;
    }

    @Override
    public void setId(String s) {
        this.studentCode = s;
    }
}
