package org.outofoffice.eida.manager.repository;

import org.outofoffice.eida.common.exception.RowNotFoundException;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public abstract class TableRepository {
    private static final String DELIMITER = ",";

    public String findShardIdByTableNameAndId(String tableName, String id) {
        String line = findLineByTableNameAndId(tableName, id);
        if (line == null) throw new RowNotFoundException(tableName, id);
        return line.split(",")[1];
    }

    protected abstract String findLineByTableNameAndId(String tableName, String id);

    public void save(String tableName, String entityId, String shardId) {
        String line = String.join(DELIMITER, entityId, shardId);
        saveLine(tableName, line);
    }

    protected abstract void saveLine(String tableName, String line);

    public Set<String> findAllShardIdsByTableName(String tableName) {
        Map<String, String> table = getTableByName(tableName);
        Collection<String> lines = table.values();
        return lines.stream().map(l -> l.split(DELIMITER)[1]).collect(toSet());
    }

    protected abstract Map<String, String> getTableByName(String tableName);

    public abstract void delete(String tableName, String id);

}
