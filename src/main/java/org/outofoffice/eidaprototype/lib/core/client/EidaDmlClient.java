package org.outofoffice.eidaprototype.lib.core.client;

import org.outofoffice.eidaprototype.lib.core.socket.EidaSocketClient;


public interface EidaDmlClient {

    void useMockClient(EidaSocketClient mockClient);

    String selectAll(String shardUrl, String tableName);

    <ID> String selectById(String shardUrl, String tableName, ID id);

    void insert(String shardUrl, String tableName, String serialized);

}
