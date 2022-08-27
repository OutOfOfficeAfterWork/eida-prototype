package org.outofoffice.eida.common.table;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Table {

    public static final String DELIMITER = ",";

    @Getter
    private final String tableName;

    private final Map<String, String> content;


    public Table(String tableName, Map<String, String> content) {
        this.tableName = tableName;
        this.content = content;
    }


    public Map<String, String> copyContent() {
        return new HashMap<>(content);
    }

    public Optional<String> getRow(String id) {
        return Optional.ofNullable(content.get(id));
    }

}
