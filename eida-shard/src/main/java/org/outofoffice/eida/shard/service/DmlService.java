package org.outofoffice.eida.shard.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.exception.DuplicatedKeyException;
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
        Map<String, String> content = table.copyContent();
        return String.join("\n", content.values());
    }

    public String selectByTableNameAndId(String tableName, String id) {
        Table table = tableService.findByName(tableName);
        return table.getRow(id).orElseThrow(() -> new RowNotFoundException(tableName, id));
    }

    public void insert(String tableName, String rowString) {
        Table table = tableService.findByName(tableName);
        String id = rowString.split(DELIMITER, 2)[0];
        if (table.getRow(id).isPresent()) throw new DuplicatedKeyException(tableName, id);

        tableService.appendRow(tableName, rowString);
    }

    public void update(String tableName, String rowString) {
        String id = rowString.split(DELIMITER, 2)[0];
        tableService.deleteRow(tableName, id);
        tableService.appendRow(tableName, rowString);
    }

    public void delete(String tableName, String id) {
        tableService.deleteRow(tableName, id);
    }

}