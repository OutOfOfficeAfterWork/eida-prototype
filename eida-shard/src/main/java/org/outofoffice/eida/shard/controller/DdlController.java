package org.outofoffice.eida.shard.controller;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.shard.service.DdlService;

@RequiredArgsConstructor
public class DdlController {
    private final DdlService ddlService;

    public void createTable(String tableName) {
        ddlService.createTable(tableName);
    }
}
