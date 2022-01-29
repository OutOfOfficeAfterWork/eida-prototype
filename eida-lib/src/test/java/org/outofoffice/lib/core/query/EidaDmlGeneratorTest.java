package org.outofoffice.lib.core.query;

import org.junit.jupiter.api.Test;
import org.outofoffice.lib.core.ui.EidaSerializer;
import org.outofoffice.lib.example.TestEidaEntity;

import static org.assertj.core.api.Assertions.assertThat;


class EidaDmlGeneratorTest {

    EidaDmlGenerator dmlGenerator = new EidaDmlGenerator();


    @Test
    void createSelectAllQuery() {
        String dmlQuery = dmlGenerator.createSelectAllQuery("member");
        assertThat(dmlQuery).isEqualTo("select all, member");
    }

    @Test
    void createSelectByIdQuery() {
        String dmlQuery = dmlGenerator.createSelectByIdQuery("member", 1L);
        assertThat(dmlQuery).isEqualTo("select, member 1");
    }

    @Test
    void createInsertQuery() {
        TestEidaEntity entity = new TestEidaEntity(1L, "kemi");
        EidaSerializer serializer = new EidaSerializer();
        String serialized = serializer.serialize(entity);
        String dmlQuery = dmlGenerator.createInsertQuery("member", serialized);
        assertThat(dmlQuery).isEqualTo("insert, member id,name 1,kemi");
    }
}