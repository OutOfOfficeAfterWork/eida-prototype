package org.outofoffice.eida.manager.core.handler.dll;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.core.controller.DllController;
import org.outofoffice.eida.manager.core.handler.QueryHandler;

@RequiredArgsConstructor
public class GetSrcQueryHandler implements QueryHandler {

    private final DllController dllController;

    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ");
        String tableName = params[0];
        String id = params[1];
        return dllController.getSourceShardUrl(tableName, id);
    }
}
