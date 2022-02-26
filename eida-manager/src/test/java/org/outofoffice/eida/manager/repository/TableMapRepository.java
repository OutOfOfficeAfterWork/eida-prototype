package org.outofoffice.eida.manager.repository;

import java.util.HashMap;
import java.util.Map;

public class TableMapRepository extends TableRepository {
    private final Map<String, Map<String, String>> map = new HashMap<>();
    private static final String DELIMITER = ",";

    @Override
    protected String findLineByTableNameAndId(String tableName, String id) {
        Map<String, String> tableFile = map.get(tableName);
        return tableFile.get(id);
    }

    @Override
    protected void saveLine(String tableName, String line) {
        Map<String, String> tableFile = map.getOrDefault(tableName, new HashMap<>());
        String key = line.split(DELIMITER)[0];
        tableFile.put(key, line);
        map.put(tableName, tableFile);
    }

}
