package org.outofoffice.lib.core.ui;

import lombok.RequiredArgsConstructor;
import org.outofoffice.lib.core.client.EidaDdlManagerClient;
import org.outofoffice.lib.core.client.EidaDdlShardClient;
import org.outofoffice.lib.core.client.EidaDllClient;


@RequiredArgsConstructor
public class EidaDdlDispatcher {
    private final EidaDllClient eidaDllClient;
    private final EidaDdlManagerClient eidaDdlManagerClient;
    private final EidaDdlShardClient eidaDdlShardClient;

//    public void alter(String tableName){
//        List<String> allShardUrls = eidaDllClient.getAllShardUrls(tableName);
//        eidaDdlManagerClient.alter(tableName);
//        allShardUrls.forEach(shardUrl -> {
//            eidaDdlShardClient.alter(shardUrl, tableName);
//        });
//    }

}
