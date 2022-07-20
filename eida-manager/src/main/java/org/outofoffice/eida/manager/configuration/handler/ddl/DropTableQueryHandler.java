package org.outofoffice.eida.manager.configuration.handler.ddl;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.QueryHandler;
import org.outofoffice.eida.manager.controller.DdlController;

@RequiredArgsConstructor
public class DropTableQueryHandler implements QueryHandler {
    private final DdlController ddlController;

    @Override
    public String handle(String parameter) {
        ddlController.dropTable(parameter);
        return null;
    }
}
