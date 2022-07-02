package org.outofoffice.lib.core.impl;

import org.junit.jupiter.api.Test;
import org.outofoffice.lib.core.ui.EidaSerializer;
import org.outofoffice.lib.example.KemiEntity;
import org.outofoffice.lib.example.TestEidaEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class EidaSerializerTest {

    private final EidaSerializer serializer = new EidaSerializer();


    @Test
    void serialize() {
        TestEidaEntity testEidaEntity = new TestEidaEntity(3L, "luffy");
        String serialize = serializer.serialize(testEidaEntity);
        assertThat(serialize).isEqualTo("3,luffy");
    }

    @Test
    void serializeWithEntity() {
        KemiEntity entity = new KemiEntity(1L, "cs", new TestEidaEntity(3L, "luffy"));
        String serialize = serializer.serialize(entity);
        assertThat(serialize).isEqualTo("1,cs,3");
    }

    @Test
    void deserializeSingleRow() {
        String schemeString = "id,name";
        String tableString = "1,testName";

        List<TestEidaEntity> entities = serializer.deserialize(schemeString, tableString, TestEidaEntity.class);

        assertThat(entities).hasSize(1);
        assertThat(entities.get(0)).isEqualTo(new TestEidaEntity(1L, "testName"));
    }

    @Test
    void deserializeMultiRow() {
        String schemeString = "id,name";
        String tableString = "1,testName\n2,newName";

        List<TestEidaEntity> entities = serializer.deserialize(schemeString, tableString, TestEidaEntity.class);

        assertThat(entities).hasSize(2);
        assertThat(entities.get(0)).isEqualTo(new TestEidaEntity(1L, "testName"));
        assertThat(entities.get(1)).isEqualTo(new TestEidaEntity(2L, "newName"));
    }

    @Test
    void deserializeWithEntity() {
        String schemeString = "id,major,testEidaEntity";
        String tableString = "1,cs,3";
        List<KemiEntity> entities = serializer.deserialize(schemeString, tableString, KemiEntity.class);
        assertThat(entities.get(0)).isEqualTo(new KemiEntity(1L, "cs", new TestEidaEntity(3L, null)));
    }

}
