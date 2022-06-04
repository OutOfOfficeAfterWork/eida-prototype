package com.example.springapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.outofoffice.lib.core.ui.EidaEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Major implements EidaEntity<String> {
    private String majorName;
    private String englishName;

    @Override
    public String getId() {
        return majorName;
    }

    @Override
    public void setId(String s) {
        majorName = s;
    }
}
