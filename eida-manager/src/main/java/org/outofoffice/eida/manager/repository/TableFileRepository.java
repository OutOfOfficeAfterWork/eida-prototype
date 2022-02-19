package org.outofoffice.eida.manager.repository;

import org.outofoffice.eida.manager.io.ManagerServerFileFacade;

import java.util.Map;

public class TableFileRepository implements TableRepository {
    @Override
    public String findShardIdByTableNameAndId(String tableName, String id) {
        Map<String, String> map = ManagerServerFileFacade.readTableFile(tableName);
        return map.get(id);
    }
}
