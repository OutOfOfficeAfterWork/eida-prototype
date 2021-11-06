package org.outofoffice.eidaprototype.lib.core;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class EidaShardClientImpl implements EidaShardClient {

    private final EidaDmlGenerator dmlGenerator;
    private EidaClient eidaClient;

    @Override
    public void useMockClient(EidaClient mockClient) {
        this.eidaClient = mockClient;
    }


    @Override
    public String selectAll(String shardUrl, String tableName) {
        String dml = dmlGenerator.createSelectAllQuery(tableName);
        return eidaClient.request(shardUrl, dml);
    }

    @Override
    public <ID> String selectById(String shardUrl, String tableName, ID id) {
        String dml = dmlGenerator.createSelectByIdQuery(shardUrl, tableName, id);
        return eidaClient.request(shardUrl, dml);
    }

    @Override
    public void insert(String shardUrl, String tableName, String serialized) {
        String dml = dmlGenerator.createInsertQuery(shardUrl, tableName, serialized);
        eidaClient.request(shardUrl, dml);
    }

}
