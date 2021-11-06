package org.outofoffice.eidaprototype.lib.core;

import java.util.List;


public interface EidaManagerClient {

    String getManagerServerUrl();

    void setManagerServerUrl(String managerServerUrl);

    List<String> getAllShardUrls(String tableName);

    String getDestinationShardUrl(String tableName);

    <ID> String getSourceShardUrl(String tableName, ID id);

}
