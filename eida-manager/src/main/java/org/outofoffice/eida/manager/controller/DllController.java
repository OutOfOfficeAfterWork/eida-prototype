package org.outofoffice.eida.manager.controller;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.service.DllService;

import java.util.Set;


@RequiredArgsConstructor
public class DllController {

    private final DllService dllService;


    public void addShard(String url) {
        dllService.addShard(url);
    }

    public Set<String> getAllShardUrls() {
        return dllService.getAllShardUrls();
    }

    public String getShardUrlsAndScheme(String tableName) {
        return dllService.getShardUrlsAndScheme(tableName);
    }

    public String getDestination(String tableName) {
        return dllService.getDestination(tableName);
    }

    public String getSource(String tableName, String id) {
        return dllService.getSource(tableName, id);
    }

    public void reportInsert(String shardUrl, String tableName, String id) {
        dllService.reportInsert(shardUrl, tableName, id);
    }

    public void reportDelete(String tableName, String id) {
        dllService.reportDelete(tableName, id);
    }

}
