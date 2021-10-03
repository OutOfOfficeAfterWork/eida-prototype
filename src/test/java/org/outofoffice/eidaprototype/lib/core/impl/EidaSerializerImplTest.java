package org.outofoffice.eidaprototype.lib.core.impl;

import org.outofoffice.eidaprototype.lib.core.EidaSerializerImpl;
import org.outofoffice.eidaprototype.lib.testing.example.TestEidaEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class EidaSerializerImplTest {

    private final EidaSerializerImpl serializer = new EidaSerializerImpl();


    @Test
    void deserialize() {
        String result = "id,name\n\r1,testName";

        TestEidaEntity deserialized = serializer.deserialize(result, TestEidaEntity.class);

        assertThat(deserialized).isEqualTo(
                new TestEidaEntity(1L, "testName"));
    }

}
