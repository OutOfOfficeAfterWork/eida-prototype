package org.outofoffice.eida.manager.repository;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class MetadataRepository {
    private static final String DELIMITER = ",";


    public String findShardUrlByShardId(String shardId) {
        String line = findLineByShardId(shardId);
        return line.split(DELIMITER)[1];
    }

    protected abstract String findLineByShardId(String shardId);


    public void save(String shardId, String shardUrl) {
        String line = String.join(DELIMITER, shardId, shardUrl);
        saveLine(line);
    }

    protected abstract void saveLine(String line);


    public List<String> findAllShardUrlsByShardIds(Collection<String> shardIds) {
        return shardIds.stream()
            .map(this::findShardUrlByShardId)
            .collect(toList());
    }

}
