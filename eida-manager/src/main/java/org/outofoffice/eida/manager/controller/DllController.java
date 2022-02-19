package org.outofoffice.eida.manager.controller;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.service.DllService;

import java.util.List;


@RequiredArgsConstructor
public class DllController {

    private final DllService dllService;


    public List<String> getAllShardUrls(String tableName) {
        return dllService.getAllShardUrls(tableName);
    }

    public String getDestinationShardUrl(String tableName) {
        return dllService.getDestinationShardUrl(tableName);
    }

    public String getSourceShardUrl(String tableName, String id) {
        return dllService.getSourceShardUrl(tableName, id);
    }

    public void reportInsertShardUrl(String shardUrl, String tableName, String id) {
        dllService.reportInsertShardUrl(shardUrl, tableName, id);
    }

    public void reportDeleteShardUrl(String shardUrl, String tableName, String id) {
        dllService.reportDeleteShardUrl(shardUrl, tableName, id);
    }

}
