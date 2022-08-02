package org.outofoffice.eida.manager.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.exception.EidaBadRequestException;
import org.outofoffice.eida.common.table.Table;
import org.outofoffice.eida.common.table.TableService;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
public class DdlService {
    private final SchemeService schemeService;
    private final TableService tableService;
    private final Partitioner partitioner;

    public void createTable(String tableName, String scheme) {
        if (tableService.existByName(tableName)) {
            throw new EidaBadRequestException("duplicated table name");
        }

        schemeService.save(tableName, scheme);
        tableService.create(tableName);
        partitioner.addTableQueue(tableName);
    }

    public void renameTable(String currentName, String nextName) {
        if (tableService.existByName(nextName)) {
            throw new EidaBadRequestException("duplicated table name");
        }

        schemeService.rename(currentName, nextName);
        tableService.rename(currentName, nextName);
        partitioner.renameTableQueue(currentName, nextName);
    }

    public void dropTable(String tableName) {
        schemeService.delete(tableName);
        tableService.drop(tableName);
        partitioner.deleteTableQueue(tableName);
    }

    public Set<String> getAllTables() {
        List<Table> tables = tableService.findAll();
        return tables.stream()
            .map(Table::getTableName)
            .collect(toSet());
    }


    public void createColumn(String tableName, String columnName) {
        schemeService.createColumn(tableName, columnName);
    }

    public void renameColumn(String tableName, String currentName, String nextName) {
        schemeService.renameColumn(tableName, currentName, nextName);
    }

    public int deleteColumn(String tableName, String columnName) {
        return schemeService.deleteColumn(tableName, columnName);
    }

}
