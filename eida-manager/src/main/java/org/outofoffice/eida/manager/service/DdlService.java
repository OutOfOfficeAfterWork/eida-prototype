package org.outofoffice.eida.manager.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.table.TableService;

@RequiredArgsConstructor
public class DdlService {
    private final SchemeService schemeService;
    private final TableService tableService;

    public void createTable(String tableName, String scheme) {
        schemeService.save(tableName, scheme);
        tableService.create(tableName);
    }

    public void renameTable(String currentName, String nextName) {
        schemeService.rename(currentName, nextName);
        tableService.rename(currentName, nextName);
    }

}
