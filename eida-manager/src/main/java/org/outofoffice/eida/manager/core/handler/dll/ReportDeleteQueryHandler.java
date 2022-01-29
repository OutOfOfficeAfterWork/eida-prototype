package org.outofoffice.eida.manager.core.handler.dll;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.core.controller.DllController;
import org.outofoffice.eida.manager.core.handler.QueryHandler;

@RequiredArgsConstructor
public class ReportDeleteQueryHandler implements QueryHandler {

    private final DllController dllController;

    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ");
        String shardUrl = params[0];
        String tableName = params[1];
        String id = params[2];
        dllController.reportDeleteShardUrl(shardUrl, tableName, id);
        return null;
    }

}
