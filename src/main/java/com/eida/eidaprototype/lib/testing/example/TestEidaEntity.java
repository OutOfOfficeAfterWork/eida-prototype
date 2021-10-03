package com.eida.eidaprototype.lib.testing.example;

import com.eida.eidaprototype.lib.core.EidaEntity;
import lombok.*;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PUBLIC;


@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PUBLIC)
@Getter @Setter @EqualsAndHashCode @ToString
public class TestEidaEntity implements EidaEntity<Long> {

    private Long id;

    private String name;

}
