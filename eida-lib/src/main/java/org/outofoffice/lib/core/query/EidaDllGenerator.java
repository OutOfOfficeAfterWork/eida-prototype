package org.outofoffice.lib.core.query;


public class EidaDllGenerator {

    public String createGetAllShardUrlsQuery(String tableName) {
        return  "get all " + tableName;
    }

    public String createGetDestinationShardUrlQuery(String tableName) {
        return "get dst " + tableName;
    }

    public <ID> String createGetSourceShardUrlQuery(String tableName, ID id) {
        return "get src " + tableName + " " + id.toString();
    }

    public <ID> String createPostShardUrlQuery(String shardUrl, String tableName, ID id) {
        return "post " + shardUrl + " " + tableName + " " + id.toString();
    }

    public <ID> String createDeleteShardUrlQuery(String shardUrl, String tableName, ID id) {
        return "delete " + shardUrl + " " + tableName + " " + id.toString();
    }

}
