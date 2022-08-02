package org.outofoffice.eida.manager.configuration.handler.ddl;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.QueryHandler;
import org.outofoffice.eida.manager.controller.DdlController;

@RequiredArgsConstructor
public class CreateColumnQueryHandler implements QueryHandler {
    private final DdlController ddlController;

    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ", 2);
        String tableName = params[0];
        String columnName = params[1];

        ddlController.createColumn(tableName, columnName);
        return null;
    }
}
