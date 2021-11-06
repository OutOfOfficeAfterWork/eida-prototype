package org.outofoffice.eidaprototype.lib.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
public class EidaManagerClientImpl implements EidaManagerClient {

    private final EidaDllGenerator dllGenerator;

    @Getter
    @Setter
    private String managerServerUrl;


    @Override
    public List<String> getAllShardUrls(String tableName) {
        String dll = dllGenerator.createGetAllShardUrls(tableName);
        return List.of("http://shard01:1234", "http://shard02:1234");
    }

    @Override
    public String getDestinationShardUrl(String tableName) {
        String dll = dllGenerator.createGetDestinationShardUrl(tableName);
        return "http://shard01:1234";
    }

    @Override
    public <ID> String getSourceShardUrl(String tableName, ID id) {
        String dll = dllGenerator.createGetSourceShardUrl(tableName, id);
        return "http://shard02:1234";
    }

}
