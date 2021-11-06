package org.outofoffice.eidaprototype.lib.core;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class EidaShardClientImpl implements EidaShardClient {

    private final EidaDmlGenerator dmlGenerator;


    @Override
    public String selectAll(String shardUrl, String tableName) {
        String dml = dmlGenerator.createSelectAllQuery(tableName);
        return "id,name\n1,testName1\n2,testName2";
    }

    @Override
    public <ID> String selectById(String shardUrl, String tableName, ID id) {
        String dml = dmlGenerator.createSelectByIdQuery(shardUrl, tableName, id);
        return "id,name\n1,testName";
    }

    @Override
    public void insert(String shardUrl, String tableName, String serialized) {
        String dml = dmlGenerator.createInsertQuery(shardUrl, tableName, serialized);

    }

}
