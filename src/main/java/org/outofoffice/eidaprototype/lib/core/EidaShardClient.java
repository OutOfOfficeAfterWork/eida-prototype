package org.outofoffice.eidaprototype.lib.core;


public interface EidaShardClient {

    String selectAll(String shardUrl, String tableName);

    <ID> String selectById(String shardUrl, String tableName, ID id);

    void insert(String shardUrl, String tableName, String serialized);

}
