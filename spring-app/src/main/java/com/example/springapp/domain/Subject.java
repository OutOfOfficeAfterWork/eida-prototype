package com.example.springapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.outofoffice.lib.core.ui.EidaEntity;
import org.outofoffice.lib.core.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject implements EidaEntity<String> {
    @Id
    private String subjectName;
    private String englishName;
    private Major major;
}
