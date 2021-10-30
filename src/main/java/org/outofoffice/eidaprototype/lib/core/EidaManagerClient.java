package org.outofoffice.eidaprototype.lib.core;


import java.util.List;

public interface EidaManagerClient {

    String getManagerServerUrl();

    void setManagerServerUrl(String managerServerUrl);

    String getShardUrl(String tableName);

    List<String> getShardUrls(String tableName);

    <ID> String getShardUrl(String tableName, ID id);

}
