package org.outofoffice.lib.core.client;

import lombok.RequiredArgsConstructor;
import org.outofoffice.lib.core.query.EidaDmlGenerator;
import org.outofoffice.common.socket.EidaSocketClient;


@RequiredArgsConstructor
public class EidaShardClient implements EidaDmlClient, EidaDdlShardClient {

    private final EidaDmlGenerator dmlGenerator;
    private final EidaSocketClient eidaClient;


    @Override
    public String selectAll(String shardUrl, String tableName) {
        String dml = dmlGenerator.createSelectAllQuery(tableName);
        return eidaClient.request(shardUrl, dml);
    }

    @Override
    public <ID> String selectById(String shardUrl, String tableName, ID id) {
        String dml = dmlGenerator.createSelectByIdQuery(tableName, id);
        return eidaClient.request(shardUrl, dml);
    }

    @Override
    public void insert(String shardUrl, String tableName, String serialized) {
        String dml = dmlGenerator.createInsertQuery(tableName, serialized);
        eidaClient.request(shardUrl, dml);
    }

    @Override
    public void update(String shardUrl, String tableName, String serialized) {
        String dml = dmlGenerator.createUpdateQuery(tableName, serialized);
        eidaClient.request(shardUrl, dml);
    }

    @Override
    public <ID> void delete(String shardUrl, String tableName, ID id) {
        String dml = dmlGenerator.createDeleteQuery(tableName, id);
        eidaClient.request(shardUrl, dml);
    }

    @Override
    public void createTable(String shardUrl, String tableName) {

    }

    @Override
    public void renameTable(String shardUrl, String currentName, String nextName) {

    }

    @Override
    public void dropTable(String shardUrl, String tableName) {

    }
}
