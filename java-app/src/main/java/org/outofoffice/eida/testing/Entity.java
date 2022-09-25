package org.outofoffice.eida.testing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.outofoffice.lib.core.ui.EidaEntity;
import org.outofoffice.lib.core.annotation.Id;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
public class Entity implements EidaEntity<Long> {

    @Id
    Long id = 100L;
    String key;
    String value;

    public static Entity of(String key, String value) {
        return new Entity(null, key, value);
    }

}
