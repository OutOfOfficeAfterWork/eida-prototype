package org.outofoffice.lib.example;

import lombok.*;
import org.outofoffice.lib.core.ui.EidaEntity;
import org.outofoffice.lib.core.ui.Id;

import static lombok.AccessLevel.PUBLIC;


@NoArgsConstructor(access = PUBLIC)
@AllArgsConstructor(access = PUBLIC)
@EqualsAndHashCode @ToString
public class TestEidaEntity implements EidaEntity<Long> {

    @Id(autoGenerated = true)
    private Long id;

    @Getter
    private String name;

}
