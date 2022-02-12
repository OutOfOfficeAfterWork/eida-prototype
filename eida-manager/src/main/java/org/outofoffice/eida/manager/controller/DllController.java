package org.outofoffice.eida.manager.controller;

import java.util.Collections;
import java.util.List;

public class DllController {
    public static final DllController INSTANCE = new DllController();


    public List<String> getAllShardUrls(String tableName) {
        // return  "get all, " + tableName;
        return Collections.emptyList();
    }

    public String getDestinationShardUrl(String tableName) {
        return "get dst, " + tableName;
    }

    public String getSourceShardUrl(String tableName, String id) {
        return "get src, " + tableName + " " + id;
    }

    public void reportInsertShardUrl(String shardUrl, String tableName, String id) {
        // return "report insert, " + shardUrl + " " + tableName + " " + id;
    }

    public void reportDeleteShardUrl(String shardUrl, String tableName, String id) {
        // return "report delete, " + shardUrl + " " + tableName + " " + id;
    }
}
