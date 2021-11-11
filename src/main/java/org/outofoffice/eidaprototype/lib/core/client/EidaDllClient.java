package org.outofoffice.eidaprototype.lib.core.client;

import java.util.List;


public interface EidaDllClient {

    String getManagerServerUrl();

    void setManagerServerUrl(String managerServerUrl);

    List<String> getAllShardUrls(String tableName);

    String getDestinationShardUrl(String tableName);

    <ID> String getSourceShardUrl(String tableName, ID id);

}
