package org.outofoffice.eida.common.table;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TableFileRepository implements TableRepository {

    private final TableFileFacade tableFileFacade;

    @Override
    public Table findByName(String tableName) {
        return tableFileFacade.findByName(tableName);
    }

    @Override
    public void save(Table table) {
        tableFileFacade.save(table);
    }

    @Override
    public List<Table> findAll() {
        return tableFileFacade.findAll();
    }

    @Override
    public void delete(Table table) {
        tableFileFacade.delete(table);
    }

    @Override
    public void clear() {
        tableFileFacade.clear();
    }
}
