package org.outofoffice.lib.example;

import lombok.*;
import org.outofoffice.lib.core.ui.EidaEntity;

@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Team implements EidaEntity<Long> {
    @Getter @Setter
    private Long id;
    @Setter
    private String teamName;
}
