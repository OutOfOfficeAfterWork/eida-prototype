package org.outofoffice.eida.common.table;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;


    public void appendRow(String tableName, String id, String shardId) {
        Table table = tableRepository.findByName(tableName);
        table.appendRow(id, shardId);
        tableRepository.save(table);
    }

    public void deleteRow(String tableName, String id) {
        Table table = tableRepository.findByName(tableName);
        table.deleteRow(id);
        tableRepository.save(table);
    }

    public Table findByName(String tableName) {
        return tableRepository.findByName(tableName);
    }
}
