package com.eida.eidaprototype.lib.testing.example;

import com.eida.eidaprototype.lib.core.EidaEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@RequiredArgsConstructor
@EqualsAndHashCode @ToString
public class TestEidaEntity implements EidaEntity<Long> {

    @Getter
    private final Long id;

}
