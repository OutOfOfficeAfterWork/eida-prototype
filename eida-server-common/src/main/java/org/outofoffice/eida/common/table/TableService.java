package org.outofoffice.eida.common.table;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;


    public void create(String tableName) {
        tableRepository.save(new Table(tableName));
    }

    public void appendRow(String tableName, String id, String value) {
        Table table = tableRepository.findByName(tableName);
        table.appendRow(id, value);
        tableRepository.save(table);
    }

    public void appendRow(String tableName, String row) {
        Table table = tableRepository.findByName(tableName);
        table.appendRow(row);
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
