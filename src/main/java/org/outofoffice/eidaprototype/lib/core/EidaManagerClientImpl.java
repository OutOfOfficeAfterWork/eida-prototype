package org.outofoffice.eidaprototype.lib.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
public class EidaManagerClientImpl implements EidaManagerClient {

    @Getter
    @Setter
    private String managerServerUrl;


    @Override
    public List<String> getAllShardUrls(String tableName) {
        return List.of("http://shard01:1234", "http://shard02:1234");
    }

    @Override
    public String getDestinationShardUrl(String tableName) {
        return "http://shard01:1234";
    }

    @Override
    public <ID> String getSourceShardUrl(String tableName, ID id) {
        return "http://shard02:1234";
    }

}
