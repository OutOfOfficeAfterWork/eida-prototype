package org.outofoffice.lib.core.client;

import lombok.RequiredArgsConstructor;
import org.outofoffice.lib.core.query.EidaDmlGenerator;
import org.outofoffice.lib.core.socket.EidaSocketClient;


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

}
