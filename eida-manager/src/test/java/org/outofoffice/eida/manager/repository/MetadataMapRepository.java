package org.outofoffice.eida.manager.repository;

import java.util.HashMap;
import java.util.Map;

public class MetadataMapRepository extends MetadataRepository {

    private static final String DELIMITER = ",";


    private final Map<String, String> map = new HashMap<>();


    @Override
    protected String findLineByShardId(String shardId) {
        return map.get(shardId);
    }

    @Override
    protected void saveLine(String line) {
        String key = line.split(DELIMITER)[0];
        map.put(key, line);
    }

    @Override
    public String findShardIdByShardUrl(String shardUrl) {
        return map.entrySet().stream()
            .filter(e -> e.getValue().split(DELIMITER)[1].equals(shardUrl))
            .map(Map.Entry::getKey)
            .findFirst().orElseThrow();
    }
}
