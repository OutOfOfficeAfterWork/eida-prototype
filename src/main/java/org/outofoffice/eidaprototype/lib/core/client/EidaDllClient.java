package org.outofoffice.eidaprototype.lib.core.client;

import org.outofoffice.eidaprototype.lib.core.socket.EidaSocketClient;

import java.util.List;


public interface EidaDllClient {

    void useMockClient(EidaSocketClient mockClient);

    String getManagerServerUrl();

    void setManagerServerUrl(String managerServerUrl);

    List<String> getAllShardUrls(String tableName);

    String getDestinationShardUrl(String tableName);

    <ID> String getSourceShardUrl(String tableName, ID id);

}
