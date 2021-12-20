package org.outofoffice.lib.core.impl;

import org.junit.jupiter.api.Test;
import org.outofoffice.lib.core.ui.EidaSerializer;
import org.outofoffice.lib.example.TestEidaEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class EidaSerializerTest {

    private final EidaSerializer serializer = new EidaSerializer();


    @Test
    void setSerializer() {
        TestEidaEntity testEidaEntity = new TestEidaEntity(3L, "luffy");
        String serialize = serializer.serialize(testEidaEntity);
        assertThat(serialize).isEqualTo("id,name 3,luffy");
    }

    @Test
    void deserializeSingleRow() {
        String tableString = "id,name\n1,testName";

        List<TestEidaEntity> entities = serializer.deserialize(tableString, TestEidaEntity.class);

        assertThat(entities).hasSize(1);
        assertThat(entities.get(0)).isEqualTo(new TestEidaEntity(1L, "testName"));
    }

    @Test
    void deserializeMultiRow() {
        String tableString = "id,name\n1,testName\n2,newName";

        List<TestEidaEntity> entities = serializer.deserialize(tableString, TestEidaEntity.class);

        assertThat(entities).hasSize(2);
        assertThat(entities.get(0)).isEqualTo(new TestEidaEntity(1L, "testName"));
        assertThat(entities.get(1)).isEqualTo(new TestEidaEntity(2L, "newName"));
    }

}