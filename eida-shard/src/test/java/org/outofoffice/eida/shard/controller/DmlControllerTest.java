package org.outofoffice.eida.shard.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DmlControllerTest {

    DmlController dmlController;

    @BeforeEach
    void setup() {
        dmlController = DmlController.INSTANCE;
    }

    @Test
    void selectById() {
        String tableName = "Team";
        String id = "1";
        String tableString = dmlController.selectById(tableName, id);

        assertThat(tableString).isEqualTo("id,teamName\n1,OutOfOffice");
    }
}