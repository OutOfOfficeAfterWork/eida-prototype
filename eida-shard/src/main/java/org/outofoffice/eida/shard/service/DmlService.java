package org.outofoffice.eida.shard.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.table.TableService;

@RequiredArgsConstructor
public class DmlService {

    private final TableService tableService;


    public String selectAll(String tableName) {
        return null;
    }

    public String selectByTableNameAndId(String tableName, String id) {
        return null;
    }

    public void insert(String tableName, String data) {
    }

    public void update(String tableName, String data) {
    }

    public void delete(String tableName, String id) {
    }

}