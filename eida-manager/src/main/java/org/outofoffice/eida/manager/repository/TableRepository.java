package org.outofoffice.eida.manager.repository;

public abstract class TableRepository {
    private static final String DELIMITER = ",";

    public String findShardIdByTableNameAndId(String tableName, String id) {
        String line = findLineByTableNameAndId(tableName, id);
        return line.split(",")[1];
    }

    protected abstract String findLineByTableNameAndId(String tableName, String id);

    public void save(String tableName, String entityId, String shardId) {
        String line = String.join(DELIMITER, entityId, shardId);
        saveLine(tableName, line);
    }

    protected abstract void saveLine(String tableName, String line);

}
