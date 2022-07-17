package org.outofoffice.eida.manager.configuration.handler.ddl;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.QueryHandler;
import org.outofoffice.eida.manager.controller.DdlController;

@RequiredArgsConstructor
public class RenameTableQueryHandler implements QueryHandler {
    private final DdlController ddlController;

    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ", 2);
        String currentName = params[0];
        String nextName = params[1];

        ddlController.renameTable(currentName, nextName);

        return null;
    }
}
