package com.eida.eidaprototype.lib.testing.example;

import com.eida.eidaprototype.lib.core.EidaEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@ToString
@RequiredArgsConstructor
public class TestEidaEntity implements EidaEntity<Long> {

    @Getter
    private final Long id;

}
