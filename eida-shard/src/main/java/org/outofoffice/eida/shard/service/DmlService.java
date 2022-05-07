package org.outofoffice.eida.shard.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.exception.RowNotFoundException;
import org.outofoffice.eida.common.table.Table;
import org.outofoffice.eida.common.table.TableService;

import java.util.Map;

import static org.outofoffice.eida.common.table.Table.DELIMITER;

@RequiredArgsConstructor
public class DmlService {

    private final TableService tableService;


    public String selectAll(String tableName) {
        Table table = tableService.findByName(tableName);
        String header = table.getHeader() + "\n";
        Map<String, String> content = table.copyContent();
        return header + String.join("\n", content.values());
    }

    public String selectByTableNameAndId(String tableName, String id) {
        Table table = tableService.findByName(tableName);
        String header = table.getHeader() + "\n";
        String rowString = table.getRow(id).orElseThrow(() -> new RowNotFoundException(tableName, id));
        return header + rowString;
    }

    public void insert(String tableName, String data) {
        String rowString = data.split(" ", 2)[1];
        tableService.appendRow(tableName, rowString);
    }

    public void update(String tableName, String data) {
        String rowString = data.split(" ", 2)[1];
        String id = rowString.split(DELIMITER, 2)[0];
        tableService.deleteRow(tableName, id);
        tableService.appendRow(tableName, rowString);
    }

    public void delete(String tableName, String id) {
        tableService.deleteRow(tableName, id);
    }

}