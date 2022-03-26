package org.outofoffice.eida.common.table;

import java.util.List;

public class TableFileRepository implements TableRepository {
    @Override
    public Table findByName(String tableName) {
        throw new IllegalStateException("todo impl");
    }

    @Override
    public void save(Table table) {
        throw new IllegalStateException("todo impl");
    }

    @Override
    public List<Table> findAll() {
        throw new IllegalStateException("todo impl");
    }

    @Override
    public void clear() {
        throw new IllegalStateException("todo impl");
    }
}
