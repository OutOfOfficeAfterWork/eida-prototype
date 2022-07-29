package org.outofoffice.eida.manager.controller;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.service.DdlService;

import java.util.Set;

@RequiredArgsConstructor
public class DdlController {
    private final DdlService ddlService;

    public void createTable(String tableName, String columns) {
        ddlService.createTable(tableName, columns);
    }

    public void renameTable(String currentName, String nextName) {
        ddlService.renameTable(currentName, nextName);
    }

    public void dropTable(String tableName) {
        ddlService.dropTable(tableName);
    }

    public Set<String> getAllTables() {
        return ddlService.getAllTables();
    }

}
