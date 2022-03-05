package org.outofoffice.eida.manager.repository;

import org.outofoffice.eida.manager.io.ManagerServerFileFacade;

import java.util.Map;

public class TableFileRepository extends TableRepository {
    @Override
    protected String findLineByTableNameAndId(String tableName, String id) {
        Map<String, String> map = getTableByName(tableName);
        return map.get(id);
    }

    @Override
    protected void saveLine(String tableName, String line) {
        throw new IllegalStateException("구현 필요");
    }

    @Override
    protected Map<String, String> getTableByName(String tableName) {
       return ManagerServerFileFacade.readTableFile(tableName);
    }
}
