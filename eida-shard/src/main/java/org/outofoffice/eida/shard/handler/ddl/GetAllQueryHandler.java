package org.outofoffice.eida.shard.handler.ddl;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.shard.controller.DdlController;
import org.outofoffice.eida.common.QueryHandler;


@RequiredArgsConstructor
public class GetAllQueryHandler implements QueryHandler {

    private final DdlController ddlController;

    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ");
        //String tableName = params[0];
        // List<String> allShardUrls = dllController.getAllShardUrls(tableName);
        // return String.join(",", allShardUrls);
        return "";
   }

}
