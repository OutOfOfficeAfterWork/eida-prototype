package org.outofoffice.eidaprototype.lib.core;


public class EidaShardClientImpl implements EidaShardClient {

    @Override
    public String selectAll(String shardUrl, String tableName) {
        return "id,name\n1,testName1\n2,testName2";
    }

    @Override
    public <ID> String selectById(String shardUrl, String tableName, ID id) {
        return "id,name\n1,testName";
    }

    @Override
    public void insert(String shardUrl, String tableName, String serialized) {

    }

}
