package org.outofoffice.eida.shard.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.outofoffice.eida.common.exception.RowNotFoundException;
import org.outofoffice.eida.common.table.Table;
import org.outofoffice.eida.common.table.TableMapRepository;
import org.outofoffice.eida.common.table.TableRepository;
import org.outofoffice.eida.common.table.TableService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DmlServiceTest {
    DmlService dmlService;
    TableRepository tableRepository;
    TableService tableService;

    @BeforeEach
    void setUp() {
        tableRepository = new TableMapRepository();
        tableService = new TableService(tableRepository);

        dmlService = new DmlService(tableService);
    }

    @AfterEach
    void clear() {
        tableRepository.clear();
    }

    @Test
    void selectAll() {
        String tableName = "user";

        Table table = new Table(tableName);
        table.appendRow("1", "kemi");
        table.appendRow("2", "josh");
        tableRepository.save(table);

        String response = dmlService.selectAll(tableName);
        assertThat(response).isEqualTo("1,kemi\n2,josh");
    }

    @Test
    void selectByTableNameAndId() {
        String tableName = "user";

        Table table = new Table(tableName);
        table.appendRow("1", "kemi");
        table.appendRow("2", "josh");
        tableRepository.save(table);

        String response = dmlService.selectByTableNameAndId(tableName, "1");
        assertThat(response).isEqualTo("1,kemi");
    }

    @Test
    void insert() {
        String tableName = "user";
        String data = "3,eida";

        Table table = new Table(tableName);
        table.appendRow("1", "kemi");
        table.appendRow("2", "josh");
        tableRepository.save(table);

        dmlService.insert(tableName, data);

        String response = dmlService.selectByTableNameAndId(tableName, "3");
        assertThat(response).isEqualTo("3,eida");
    }

    @Test
    void update() {
        String tableName = "user";
        String data = "1,josh";

        Table table = new Table(tableName);
        table.appendRow("1", "kemi");
        tableRepository.save(table);

        dmlService.update(tableName, data);

        String response = dmlService.selectByTableNameAndId(tableName, "1");
        assertThat(response).isEqualTo("1,josh");
    }

    @Test
    void delete() {
        String tableName = "user";

        Table table = new Table(tableName);
        table.appendRow("1", "kemi");
        tableRepository.save(table);

        dmlService.delete(tableName, "1");

        Executable action = () -> dmlService.selectByTableNameAndId(tableName, "1");
        assertThrows(RowNotFoundException.class, action);
    }
}