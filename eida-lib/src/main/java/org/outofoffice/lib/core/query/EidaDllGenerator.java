package org.outofoffice.lib.core.query;


public class EidaDllGenerator {

    public String createGetAllShardUrls(String tableName) {
        String dll = "get all";
        if (!tableName.equals("")) {
            dll += " " + tableName;
        }
        return dll;
    }

    public String createGetDestinationShardUrl(String tableName) {
        return "get dst " + tableName;
    }

    public <ID> String createGetSourceShardUrl(String tableName, ID id) {
        return "get src " + tableName + " " + id.toString();
    }

    public <ID> String createPostDesignatedShardUrl(String shardUrl, String tableName, ID id) {
        return "post " + shardUrl + " " + tableName + " " + id.toString();
    }

}
