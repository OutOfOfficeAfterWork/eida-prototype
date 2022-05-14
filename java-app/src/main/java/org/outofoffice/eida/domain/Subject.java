package org.outofoffice.eida.domain;

import lombok.AllArgsConstructor;
import org.outofoffice.lib.core.ui.EidaEntity;

@AllArgsConstructor
public class Subject implements EidaEntity<String> {
    private String subjectName;
    private String englishName;
    private Major major;

    @Override
    public String getId() {
        return subjectName;
    }

    @Override
    public void setId(String s) {
        subjectName = s;
    }
}
