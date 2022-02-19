package org.outofoffice.eida.manager.service;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.repository.MetadataRepository;
import org.outofoffice.eida.manager.repository.TableRepository;

import java.util.Collections;
import java.util.List;


@RequiredArgsConstructor
public class DllService {

    public final TableRepository tableRepository;
    public final MetadataRepository metadataRepository;


    public List<String> getAllShardUrls(String tableName) {
        // return  "get all, " + tableName;
        return Collections.emptyList();
    }

    public String getDestinationShardUrl(String tableName) {
        return "get dst, " + tableName;
    }

    public String getSourceShardUrl(String tableName, String id) {
        String shardId = tableRepository.findShardIdByTableNameAndId(tableName, id);
        return metadataRepository.findShardUrlByShardId(shardId);
    }

    public void reportInsertShardUrl(String shardUrl, String tableName, String id) {
        // return "report insert, " + shardUrl + " " + tableName + " " + id;
    }

    public void reportDeleteShardUrl(String shardUrl, String tableName, String id) {
        // return "report delete, " + shardUrl + " " + tableName + " " + id;
    }

}
