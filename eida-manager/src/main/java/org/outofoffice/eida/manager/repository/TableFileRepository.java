package org.outofoffice.eida.manager.repository;

import org.outofoffice.eida.manager.io.ManagerServerFileFacade;

import java.util.Map;

public class TableFileRepository extends TableRepository {
    @Override
    protected String findLineByTableNameAndId(String tableName, String id) {
        Map<String, String> map = ManagerServerFileFacade.readTableFile(tableName);
        return map.get(id);
    }

    @Override
    protected void saveLine(String tableName, String line) {

    }
}
