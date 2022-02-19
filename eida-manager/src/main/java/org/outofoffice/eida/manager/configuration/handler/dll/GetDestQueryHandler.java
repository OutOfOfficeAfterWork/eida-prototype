package org.outofoffice.eida.manager.configuration.handler.dll;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.controller.DllController;
import org.outofoffice.eida.common.QueryHandler;

@RequiredArgsConstructor
public class GetDestQueryHandler implements QueryHandler {

    private final DllController dllController;

    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ");
        String tableName = params[0];
        return dllController.getDestinationShardUrl(tableName);
    }
}
