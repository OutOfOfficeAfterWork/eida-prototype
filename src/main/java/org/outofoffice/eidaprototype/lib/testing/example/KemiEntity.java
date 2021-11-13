package org.outofoffice.eidaprototype.lib.testing.example;

import lombok.*;
import org.outofoffice.eidaprototype.lib.core.ui.EidaEntity;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PUBLIC;


@NoArgsConstructor(access = PUBLIC)
@AllArgsConstructor(access = PUBLIC)
@Getter @Setter @EqualsAndHashCode @ToString
public class KemiEntity implements EidaEntity<Long> {

    private Long id;

    private String major;

    private TestEidaEntity testEidaEntity;

}
