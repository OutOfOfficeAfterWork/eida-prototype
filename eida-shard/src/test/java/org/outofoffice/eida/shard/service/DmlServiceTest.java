package org.outofoffice.eida.shard.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.outofoffice.eida.common.exception.RowNotFoundException;
import org.outofoffice.eida.common.io.EidaFileFacade;
import org.outofoffice.eida.common.io.EidaTestFileFacade;
import org.outofoffice.eida.common.table.TableService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DmlServiceTest {

    DmlService dmlService;

    EidaFileFacade eidaFileFacade;
    TableService tableService;

    @BeforeEach
    void setUp() {
        eidaFileFacade = new EidaTestFileFacade();

        tableService = new TableService("/Table", eidaFileFacade);

        dmlService = new DmlService(tableService);
    }


    @Test
    void selectAll() {
        String tableName = "user";

        tableService.create(tableName);
        tableService.appendRow(tableName, "1", "kemi");
        tableService.appendRow(tableName, "2", "josh");

        String response = dmlService.selectAll(tableName);
        assertThat(response).isEqualTo("1,kemi\n2,josh");
    }

    @Test
    void selectByTableNameAndId() {
        String tableName = "user";

        tableService.create(tableName);
        tableService.appendRow(tableName, "1", "kemi");
        tableService.appendRow(tableName, "2", "josh");

        String response = dmlService.selectByTableNameAndId(tableName, "1");
        assertThat(response).isEqualTo("1,kemi");
    }

    @Test
    void insert() {
        String tableName = "user";
        String data = "3,eida";

        tableService.create(tableName);
        tableService.appendRow(tableName, "1", "kemi");
        tableService.appendRow(tableName, "2", "josh");

        dmlService.insert(tableName, data);

        String response = dmlService.selectByTableNameAndId(tableName, "3");
        assertThat(response).isEqualTo("3,eida");
    }

    @Test
    void update() {
        String tableName = "user";
        String data = "1,josh";

        tableService.create(tableName);
        tableService.appendRow(tableName, "1", "kemi");

        dmlService.update(tableName, data);

        String response = dmlService.selectByTableNameAndId(tableName, "1");
        assertThat(response).isEqualTo("1,josh");
    }

    @Test
    void delete() {
        String tableName = "user";

        tableService.create(tableName);
        tableService.appendRow(tableName, "1", "kemi");

        dmlService.delete(tableName, "1");

        Executable action = () -> dmlService.selectByTableNameAndId(tableName, "1");
        assertThrows(RowNotFoundException.class, action);
    }
}