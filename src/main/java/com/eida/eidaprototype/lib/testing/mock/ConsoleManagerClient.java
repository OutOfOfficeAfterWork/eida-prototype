package com.eida.eidaprototype.lib.testing.mock;

import com.eida.eidaprototype.lib.core.EidaManagerClient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static com.eida.eidaprototype.lib.util.LogUtils.indentedFormat;


@Slf4j
@AllArgsConstructor
public class ConsoleManagerClient implements EidaManagerClient {

    @Getter
    @Setter
    private String managerServerUrl;


    @Override
    public String getShardUrl(String tableName) {
        log.info(indentedFormat("request shard url", "for new entity of {}", "to {}"), tableName, managerServerUrl);
        return "http://shard01:1234";
    }

    @Override
    public <ID> String getShardUrl(String tableName, ID id) {
        log.info(indentedFormat("request shard url", "for {} of {}", "to {}"), id, tableName, managerServerUrl);
        return "http://shard02:1234";
    }

}
