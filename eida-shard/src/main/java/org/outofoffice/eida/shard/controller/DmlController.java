package org.outofoffice.eida.shard.controller;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.shard.service.DmlService;


@RequiredArgsConstructor
public class DmlController {

    private final DmlService dmlService;


    public String selectAll(String tableName) {
        return dmlService.selectAll(tableName);
    }

    public String selectByTableNameAndId(String tableName, String id) {
        return dmlService.selectByTableNameAndId(tableName, id);
    }

    public void insert(String tableName, String data) {
        dmlService.insert(tableName, data);
    }

    public void update(String tableName, String data) {
        dmlService.update(tableName, data);
    }

    public void delete(String tableName, String id) {
        dmlService.delete(tableName, id);
    }

}
