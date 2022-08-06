package org.outofoffice.eida.common.table;

import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;

    public void create(String tableName) {
        tableRepository.save(new Table(tableName));
    }

    public void rename(String currentName, String nextName) {
        Table currentTable = tableRepository.findByName(currentName);
        Table renamedTable = currentTable.renamed(nextName);
        tableRepository.save(renamedTable);
        tableRepository.delete(currentTable);
    }

    public void drop(String tableName) {
        Table table = tableRepository.findByName(tableName);
        tableRepository.delete(table);
    }

    public void appendColumn(String tableName, String value) {
        Table table = tableRepository.findByName(tableName);
        table.appendColumn(value);
        tableRepository.save(table);
    }

    public void deleteColumn(String tableName, int columnIndex) {
        Table table = tableRepository.findByName(tableName);
        table.deleteColumn(columnIndex);
        tableRepository.save(table);
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

    public List<Table> findAll() {
        return tableRepository.findAll();
    }

    public boolean existByName(String tableName) {
        return tableRepository.existByName(tableName);
    }
}
