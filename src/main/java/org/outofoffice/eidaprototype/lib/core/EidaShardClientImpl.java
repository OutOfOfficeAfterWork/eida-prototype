package org.outofoffice.eidaprototype.lib.core;


public class EidaShardClientImpl implements EidaShardClient {

    @Override
    public <ID> String select(String shardUrl, String tableName, ID id) {
        return "id,name\n1,testName";
    }

    @Override
    public void insert(String shardUrl, String tableName, String serialized) {

    }

    @Override
    public String select(String shardUrl, String tableName) {
        return "id,name\n1,testName";
    }

}
