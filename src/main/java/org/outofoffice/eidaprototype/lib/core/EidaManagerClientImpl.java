package org.outofoffice.eidaprototype.lib.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;


@AllArgsConstructor
public class EidaManagerClientImpl implements EidaManagerClient {

    private final EidaDllGenerator dllGenerator;
    private EidaClient eidaClient;

    @Getter
    @Setter
    private String managerServerUrl;

    @Override
    public void useMockClient(EidaClient mockClient) {
        this.eidaClient = mockClient;
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
