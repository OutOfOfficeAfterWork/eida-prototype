package org.outofoffice.eida.manager.infrastructure;

import org.outofoffice.eida.manager.repository.SchemeRepository;

import java.util.HashMap;
import java.util.Map;

public class SchemeMockRepository implements SchemeRepository {

    private final Map<String, String> map = new HashMap<>();

    @Override
    public void save(String tableName, String scheme) {
        map.put(tableName, scheme);
    }

    @Override
    public String findByName(String tableName) {
        return map.get(tableName);
    }
}
