package org.outofoffice.eida.manager.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.outofoffice.eida.manager.configuration.SingletonContainer;

import static org.assertj.core.api.Assertions.assertThat;

class DllControllerTest {

    DllController dllController;

    @BeforeEach
    void setup() {
        dllController = SingletonContainer.DLL_CONTROLLER;
    }

    @Test
    void test() {
        String tableName = "Team";
        String id = "1";
        String sourceShardUrl = dllController.getSourceShardUrl(tableName, id);

        assertThat(sourceShardUrl).isEqualTo("localhost:10830");
    }

}