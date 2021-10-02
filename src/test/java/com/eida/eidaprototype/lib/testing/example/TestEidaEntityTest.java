package com.eida.eidaprototype.lib.testing.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class TestEidaEntityTest {

    @Test
    @DisplayName("엔티티는 getId()를 구현하여야 한다.")
    void entityShouldImplGetId(){
        Long givenId = 1L;
        TestEidaEntity entity = new TestEidaEntity(givenId);

        Long id = entity.getId();

        assertThat(id).isEqualTo(givenId);
    }

    @Test
    @DisplayName("엔티티는 tableName으로 className을 갖는다.")
    void entityHasTableNameByClassName(){
        TestEidaEntity entity = new TestEidaEntity(1L);

        String tableName = entity.getTableName();

        String className = entity.getClass().getSimpleName();
        assertThat(tableName).isEqualTo(className);
    }

}
