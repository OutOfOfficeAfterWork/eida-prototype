package com.eida.eidaprototype.lib.core;


public interface EidaShardClient {

    <ID> String select(String shardUrl, String tableName, ID id);

    void insert(String shardUrl, String tableName, String serialized);

}
