package org.outofoffice.eidaprototype.lib.core.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.outofoffice.eidaprototype.lib.core.query.EidaDllGenerator;
import org.outofoffice.eidaprototype.lib.core.socket.EidaSocketClient;
import org.outofoffice.eidaprototype.lib.core.socket.EidaSocketClientLoggingProxy;

import java.util.Arrays;
import java.util.List;


@AllArgsConstructor
public class EidaManagerClient implements EidaDllClient, EidaDdlManagerClient {

    private final EidaDllGenerator dllGenerator;
    private EidaSocketClient eidaClient;

    @Getter
    @Setter
    private String managerServerUrl;

    @Override
    public void useMockClient(EidaSocketClient mockClient) {
        this.eidaClient = new EidaSocketClientLoggingProxy(mockClient);
    }


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
