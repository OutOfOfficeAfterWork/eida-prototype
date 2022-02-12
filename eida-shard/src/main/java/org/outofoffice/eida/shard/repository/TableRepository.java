package org.outofoffice.eida.shard.repository;

public interface TableRepository {
    String findByTableNameAndId(String tableName, String id);
}
