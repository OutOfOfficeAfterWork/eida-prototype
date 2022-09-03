package org.outofoffice.lib.example;

import lombok.*;
import org.outofoffice.lib.core.ui.EidaEntity;
import org.outofoffice.lib.core.ui.Id;

@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Team implements EidaEntity<Long> {
    @Id
    private Long id;
    @Setter
    private String teamName;
}
