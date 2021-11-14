package org.outofoffice.eidaprototype.lib.core.client;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eidaprototype.lib.core.query.EidaDllGenerator;
import org.outofoffice.eidaprototype.lib.core.socket.EidaSocketClient;

import java.util.Arrays;
import java.util.List;


@RequiredArgsConstructor
public class EidaManagerClient implements EidaDllClient, EidaDdlManagerClient {

    private final String managerServerUrl;
    private final EidaDllGenerator dllGenerator;
    private final EidaSocketClient eidaClient;


    @Override
    public List<String> getAllShardUrls(String tableName) {
        String dll = dllGenerator.createGetAllShardUrls(tableName);
        String response = eidaClient.request(managerServerUrl, dll);
        return Arrays.asList(response.split(","));
    }

    @Override
    public String getDestinationShardUrl(String tableName) {
        String dll = dllGenerator.createGetDestinationShardUrl(tableName);
        return eidaClient.request(managerServerUrl, dll);
    }

    @Override
    public <ID> String getSourceShardUrl(String tableName, ID id) {
        String dll = dllGenerator.createGetSourceShardUrl(tableName, id);
        return eidaClient.request(managerServerUrl, dll);
    }

}
