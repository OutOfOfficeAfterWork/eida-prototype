package org.outofoffice.lib.example;

import lombok.*;
import org.outofoffice.lib.core.ui.EidaEntity;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PUBLIC;


@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @EqualsAndHashCode @ToString
public class TestEidaEntity implements EidaEntity<Long> {

    private Long id;

    private String name;

}
