package org.outofoffice.lib.core.client;

import lombok.RequiredArgsConstructor;
import org.outofoffice.lib.core.query.EidaDllGenerator;
import org.outofoffice.common.socket.EidaSocketClient;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;


@RequiredArgsConstructor
public class EidaManagerClient implements EidaDllClient, EidaDdlManagerClient {

    private final EidaDllGenerator dllGenerator;
    private final EidaSocketClient eidaClient;

    private final String managerServerUrl;


    @Override
    public String getSources(String tableName) {
        String dll = dllGenerator.createGetAllShardUrlsQuery(tableName);
        return eidaClient.request(managerServerUrl, dll);
    }

    @Override
    public String getDestination(String tableName) {
        String dll = dllGenerator.createGetDestinationShardUrlQuery(tableName);
        return eidaClient.request(managerServerUrl, dll);
    }

    @Override
    public <ID> String getSource(String tableName, ID id) {
        String dll = dllGenerator.createGetSourceShardUrlQuery(tableName, id);
        return eidaClient.request(managerServerUrl, dll);
    }

    @Override
    public <ID> void postShardUrl(String shardUrl, String tableName, ID id) {
        String dll = dllGenerator.createReportInsertShardUrlQuery(shardUrl, tableName, id);
        eidaClient.request(managerServerUrl, dll);
    }

    @Override
    public <ID> void deleteShardUrl(String shardUrl, String tableName, ID id) {
        String dll = dllGenerator.createReportDeleteShardUrlQuery(shardUrl, tableName, id);
        eidaClient.request(managerServerUrl, dll);
    }

}
