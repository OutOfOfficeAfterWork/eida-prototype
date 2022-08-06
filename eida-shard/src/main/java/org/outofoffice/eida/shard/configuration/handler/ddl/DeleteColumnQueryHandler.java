package org.outofoffice.eida.shard.configuration.handler.ddl;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.QueryHandler;
import org.outofoffice.eida.shard.controller.DdlController;

@RequiredArgsConstructor
public class DeleteColumnQueryHandler implements QueryHandler {

    private final DdlController ddlController;

    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ", 2);
        String tableName = params[0];
        int columnIndex = Integer.parseInt(params[1]);
        ddlController.deleteColumn(tableName, columnIndex);
        return null;
    }
}
