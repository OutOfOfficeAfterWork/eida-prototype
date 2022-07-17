package org.outofoffice.eida.common.table;

import lombok.extern.slf4j.Slf4j;
import org.outofoffice.eida.common.exception.TableNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class TableMapRepository implements TableRepository {
    private final Map<String, Table> map = new HashMap<>();

    @Override
    public Table findByName(String tableName) {
        if (!map.containsKey(tableName)) throw new TableNotFoundException(new IllegalStateException(tableName));
        return map.get(tableName);
    }

    @Override
    public void save(Table table) {
        map.put(table.getTableName(), table);
        log.debug("post save: \n\t{}", String.join("\n\t", findByName(table.getTableName()).copyContent().values()));
    }

    @Override
    public List<Table> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void delete(Table table) {
        map.remove(table.getTableName());
    }

    @Override
    public void clear() {
        map.clear();
    }
}
