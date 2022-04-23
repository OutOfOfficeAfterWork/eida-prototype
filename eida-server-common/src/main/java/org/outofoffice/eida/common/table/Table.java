package org.outofoffice.eida.common.table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class Table {
    private static final String DELIMITER = ",";

    @Getter
    private final String tableName;
    private final Map<String, String> content = new HashMap<>();


    public Map<String, String> copyContent() {
        return new HashMap<>(content);
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
}
