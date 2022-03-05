package org.outofoffice.eida.manager.configuration.handler.dll;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.controller.DllController;
import org.outofoffice.eida.common.QueryHandler;

@RequiredArgsConstructor
public class ReportDeleteQueryHandler implements QueryHandler {

    private final DllController dllController;

    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ");
        String shardUrl = params[0];
        String tableName = params[1];
        String id = params[2];
        dllController.reportDelete(tableName, id);
        return null;
    }

}
