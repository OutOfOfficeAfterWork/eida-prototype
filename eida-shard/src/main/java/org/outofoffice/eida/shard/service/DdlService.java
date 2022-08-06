package org.outofoffice.eida.shard.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.table.TableService;

@RequiredArgsConstructor
public class DdlService {

    private final TableService tableService;

    public void createTable(String tableName) {
        tableService.create(tableName);
    }

    public void renameTable(String currentName, String nextName) {
        tableService.rename(currentName, nextName);
    }

    public void dropTable(String tableName) {
        tableService.drop(tableName);
    }

    public void createColumn(String tableName, String defaultValue) {
        tableService.appendColumn(tableName, defaultValue);
    }

    public void deleteColumn(String tableName, int columnIndex) {
        tableService.deleteColumn(tableName, columnIndex);
    }
}
