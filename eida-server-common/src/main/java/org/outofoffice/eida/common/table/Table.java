package org.outofoffice.eida.common.table;

import lombok.Getter;
import org.outofoffice.eida.common.exception.EidaBadRequestException;

import java.util.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

public class Table {

    public static final String DELIMITER = ",";

    @Getter
    private final String tableName;

    private final Map<String, String> content;


    public Table(String tableName) {
        this.tableName = tableName;
        this.content = new HashMap<>();
    }

    public Table(String tableName, Map<String, String> content) {
        this.tableName = tableName;
        this.content = content;
    }


    public Map<String, String> copyContent() {
        return new HashMap<>(content);
    }

    public void appendRow(String row) {
        content.put(row.split(DELIMITER, 2)[0], row);
    }

    public void appendRow(String id, String value) {
        content.put(id, String.join(DELIMITER, id, value));
    }

    public void deleteRow(String id) {
        content.remove(id);
    }

    public Optional<String> getRow(String id) {
        return Optional.ofNullable(content.get(id));
    }

    public Table renamed(String nextName) {
        return new Table(nextName, copyContent());
    }

    public void appendColumn(String value) {
        content.forEach((k, v) -> {
            String row = v + "," + value;
            content.put(k, row);
        });
    }

    public void deleteColumn(int columnIndex) {
        content.forEach((k, v) -> {
            List<String> columns = new ArrayList<>(Arrays.asList(v.split(",")));
            columns.remove(columnIndex);
            String row = String.join(",", columns);
            content.put(k, row);
        });
    }
}
