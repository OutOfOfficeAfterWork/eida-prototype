package org.outofoffice.eida.manager.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.repository.MetadataRepository;
import org.outofoffice.eida.manager.repository.TableRepository;

import java.util.List;
import java.util.Set;


@RequiredArgsConstructor
public class DllService {

    private final TableRepository tableRepository;
    private final MetadataRepository metadataRepository;

    private final Partitioner partitioner;


    public List<String> getAllShardUrls(String tableName) {
        Set<String> shardIds = tableRepository.findAllShardIdsByTableName(tableName);
        return metadataRepository.findAllShardUrlsByShardIds(shardIds);
    }

    public String getDestinationShardUrl(String tableName) {
        String shardId = partitioner.nextShardId(tableName);
        return metadataRepository.findShardUrlByShardId(shardId);
    }

    public String getSourceShardUrl(String tableName, String id) {
        String shardId = tableRepository.findShardIdByTableNameAndId(tableName, id);
        return metadataRepository.findShardUrlByShardId(shardId);
    }

    public void reportInsert(String shardUrl, String tableName, String id) {
        String shardId = metadataRepository.findShardIdByShardUrl(shardUrl);
        tableRepository.save(tableName, id, shardId);
        partitioner.arrange(tableName);
    }

    public void reportDelete(String tableName, String id) {
        tableRepository.delete(tableName, id);
    }

}
