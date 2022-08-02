package org.outofoffice.eida.manager.configuration.handler.ddl;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.QueryHandler;
import org.outofoffice.eida.manager.controller.DdlController;

@RequiredArgsConstructor
public class RenameColumnQueryHandler implements QueryHandler {
    private final DdlController ddlController;

    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ", 3);
        String tableName = params[0];
        String currentName = params[1];
        String nextName = params[2];

        ddlController.renameColumn(tableName, currentName, nextName);
        return null;
    }
}
