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
    public String getShardUrl(String tableName) {
        return "http://shard01:1234";
    }

    @Override
    public List<String> getShardUrls(String tableName) {
        return List.of("http://shard01:1234", "http://shard02:1234");
    }

    @Override
    public <ID> String getShardUrl(String tableName, ID id) {
        return "http://shard02:1234";
    }

}
