package org.outofoffice.lib.core.client;

import java.util.List;


public interface EidaDllClient {

    List<String> getAllShardUrls(String tableName);

    String getDestinationShardUrl(String tableName);

    <ID> String getSourceShardUrl(String tableName, ID id);

    <ID> void postShardUrl(String shardUrl, String tableName, ID id);

    <ID> void deleteShardUrl(String sourceShardUrl, String tableName, ID id);
}
