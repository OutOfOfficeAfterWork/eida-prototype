package org.outofoffice.eida.manager.repository;

import java.util.HashMap;
import java.util.Map;

public class MetadataMapRepository extends MetadataRepository {

    private final Map<String, String> map = new HashMap<>();


    @Override
    protected String findLineByShardId(String shardId) {
        return map.get(shardId);
    }

    @Override
    protected void saveLine(String line) {
        String key = line.split(",")[0];
        map.put(key, line);
    }
}
