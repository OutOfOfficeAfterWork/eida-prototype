package com.example.springapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.outofoffice.lib.core.ui.EidaEntity;
import org.outofoffice.lib.core.ui.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements EidaEntity<String> {
    @Id
    private String studentCode;
    private String name;
    private String birthDate;
    private String gender;
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
