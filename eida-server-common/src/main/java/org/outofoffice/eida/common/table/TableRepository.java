package org.outofoffice.eida.common.table;

import java.util.List;

public interface TableRepository {
    Table findByName(String tableName);
    void save(Table table);
    List<Table> findAll();
    void delete(Table table);
    void clear();
}
