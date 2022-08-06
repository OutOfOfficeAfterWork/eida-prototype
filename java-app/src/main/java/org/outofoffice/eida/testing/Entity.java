package org.outofoffice.eida.testing;

import lombok.*;
import org.outofoffice.lib.core.ui.EidaEntity;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class Entity implements EidaEntity<String> {

    String key;
    String value;

    @Override
    public String getId() {
        return key;
    }

    @Override
    public void setId(String key) {
        this.key = key;
    }
}
