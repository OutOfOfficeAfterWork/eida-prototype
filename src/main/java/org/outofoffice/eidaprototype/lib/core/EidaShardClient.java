package org.outofoffice.eidaprototype.lib.core;


import java.util.List;

public interface EidaShardClient {

    <ID> String select(String shardUrl, String tableName, ID id);

    void insert(String shardUrl, String tableName, String serialized);

    String select(String shardUrl, String tableName);
}
