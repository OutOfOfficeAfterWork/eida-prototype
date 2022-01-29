package org.outofoffice.eida.manager.core.controller;

public class DllController {
    public static final DllController INSTANCE = new DllController();

    public String createGetAllShardUrlsQuery(String tableName) {
        return  "get all, " + tableName;
    }

    public String createGetDestinationShardUrlQuery(String tableName) {
        return "get dst, " + tableName;
    }

    public String createGetSourceShardUrlQuery(String tableName, String id) {
        return "get src, " + tableName + " " + id;
    }

    public String createReportInsertShardUrlQuery(String shardUrl, String tableName, String id) {
        return "report insert, " + shardUrl + " " + tableName + " " + id;
    }

    public String createReportDeleteShardUrlQuery(String shardUrl, String tableName, String id) {
        return "report delete, " + shardUrl + " " + tableName + " " + id;
    }
}
