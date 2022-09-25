package org.outofoffice.lib.example;

import lombok.*;
import org.outofoffice.lib.core.ui.EidaEntity;
import org.outofoffice.lib.core.annotation.Id;


@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @EqualsAndHashCode @ToString
public class KemiEntity implements EidaEntity<Long> {

    @Id
    private Long id;

    private String major;

    private TestEidaEntity testEidaEntity;

}
