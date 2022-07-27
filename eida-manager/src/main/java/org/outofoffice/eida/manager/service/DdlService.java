package org.outofoffice.eida.manager.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.table.TableService;

@RequiredArgsConstructor
public class DdlService {
    private final SchemeService schemeService;
    private final TableService tableService;
    private final Partitioner partitioner;

    public void createTable(String tableName, String scheme) {
        schemeService.save(tableName, scheme);
        tableService.create(tableName);
        partitioner.addTableQueue(tableName);
    }

    public void renameTable(String currentName, String nextName) {
        schemeService.rename(currentName, nextName);
        tableService.rename(currentName, nextName);
        partitioner.renameTableQueue(currentName, nextName);
    }

    public void dropTable(String tableName) {
        schemeService.delete(tableName);
        tableService.drop(tableName);
        partitioner.deleteTableQueue(tableName);
    }
}
