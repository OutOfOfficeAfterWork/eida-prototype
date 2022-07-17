package org.outofoffice.eida.shard.configuration.handler.ddl;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.QueryHandler;
import org.outofoffice.eida.shard.controller.DdlController;

@RequiredArgsConstructor
public class CreateTableQueryHandler implements QueryHandler {

    private final DdlController ddlController;

    @Override
    public String handle(String parameter) {
        ddlController.createTable(parameter);
        return null;
    }
}
