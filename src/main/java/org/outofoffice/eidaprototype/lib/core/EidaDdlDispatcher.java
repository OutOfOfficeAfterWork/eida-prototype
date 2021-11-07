package org.outofoffice.eidaprototype.lib.core;

import lombok.RequiredArgsConstructor;


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
