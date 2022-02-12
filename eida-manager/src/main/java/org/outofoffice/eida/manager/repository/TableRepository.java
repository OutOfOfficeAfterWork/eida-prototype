package org.outofoffice.eida.manager.repository;

public interface TableRepository {
    String findShardIdByTableNameAndId(String tableName, String id);
}
