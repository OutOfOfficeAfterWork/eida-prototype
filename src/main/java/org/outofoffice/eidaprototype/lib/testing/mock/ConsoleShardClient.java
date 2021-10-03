package org.outofoffice.eidaprototype.lib.testing.mock;

import org.outofoffice.eidaprototype.lib.core.EidaShardClient;
import lombok.extern.slf4j.Slf4j;

import static org.outofoffice.eidaprototype.lib.util.LogUtils.indentedFormat;


@Slf4j
public class ConsoleShardClient implements EidaShardClient {

    @Override
    public <ID> String select(String shardUrl, String tableName, ID id) {
        log.info(indentedFormat("query select {} from {}", "to {}"), id, tableName, shardUrl);
        return "id,name\n\r1,testName";
    }

    @Override
    public void insert(String shardUrl, String tableName, String serialized) {
        log.info(indentedFormat("query insert {} into {}", "to {}"), serialized, tableName, shardUrl);
    }

}
