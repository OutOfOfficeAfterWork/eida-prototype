package org.outofoffice.eida.manager.repository;

import org.outofoffice.eida.manager.io.ManagerServerFileFacade;

import java.util.Map;

public class MetadataFileRepository extends MetadataRepository {
    @Override
    protected String findLineByShardId(String shardId) {
        Map<String, String> map = ManagerServerFileFacade.readShardFile();
        return map.get(shardId);
    }

    @Override
    protected void saveLine(String line) {
        // ManagerServerFileFacade.e();
        throw new IllegalStateException("구현 필요");
    }

    @Override
    public String findShardIdByShardUrl(String shardUrl) {
        throw new IllegalStateException("구현 필요");
    }
}
