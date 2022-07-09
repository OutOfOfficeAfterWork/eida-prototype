package org.outofoffice.lib.core.client;

public interface EidaDdlShardClient {
    void createTable(String shardUrl, String tableName);
    void renameTable(String shardUrl, String currentName, String nextName);
    void dropTable(String shardUrl, String tableName);
}
