package org.outofoffice.eida.manager.configuration.handler.ddl;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.QueryHandler;
import org.outofoffice.eida.manager.controller.DdlController;

import java.util.Set;

@RequiredArgsConstructor
public class GetAllTableQueryHandler implements QueryHandler {
    private final DdlController ddlController;

    @Override
    public String handle(String parameter) {
        Set<String> allTables = ddlController.getAllTables();
        return String.join(",", allTables);
    }
}
