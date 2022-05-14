package org.outofoffice.eida.domain;

import lombok.AllArgsConstructor;
import org.outofoffice.lib.core.ui.EidaEntity;

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
