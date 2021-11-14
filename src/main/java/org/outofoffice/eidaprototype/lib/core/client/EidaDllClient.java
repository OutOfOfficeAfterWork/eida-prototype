package org.outofoffice.eidaprototype.lib.core.client;

import java.util.List;


public interface EidaDllClient {

    List<String> getAllShardUrls(String tableName);

    String getDestinationShardUrl(String tableName);

    <ID> String getSourceShardUrl(String tableName, ID id);

}
