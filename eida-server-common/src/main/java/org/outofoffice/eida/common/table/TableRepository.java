package org.outofoffice.eida.common.table;

import java.util.List;

public interface TableRepository {
    void save(Table table);
    void delete(Table table);
    List<Table> findAll();
    Table findByName(String tableName);
    boolean existByName(String tableName);
    void clear();
}
