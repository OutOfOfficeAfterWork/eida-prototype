package org.outofoffice.eida.testing;

import lombok.*;
import org.outofoffice.lib.core.ui.EidaEntity;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class Entity implements EidaEntity<Long> {

    Long id;
    String key;
    String value;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public static Entity of(String key, String value) {
        return new Entity(null, key, value);
    }

}
