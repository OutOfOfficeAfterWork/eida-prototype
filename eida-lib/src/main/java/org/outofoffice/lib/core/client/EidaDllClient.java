package org.outofoffice.lib.core.client;


public interface EidaDllClient {

    String getSources(String tableName);

    String getDestination(String tableName);

    <ID> String getSource(String tableName, ID id);

    <ID> void postShardUrl(String shardUrl, String tableName, ID id);

    <ID> void deleteShardUrl(String sourceShardUrl, String tableName, ID id);
}
