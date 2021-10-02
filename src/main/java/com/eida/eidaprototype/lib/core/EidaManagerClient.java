package com.eida.eidaprototype.lib.core;


public interface EidaManagerClient {

    String getManagerServerUrl();

    void setManagerServerUrl(String managerServerUrl);


    String getShardUrl(String tableName);

    <ID> String getShardUrl(String tableName, ID id);

}
