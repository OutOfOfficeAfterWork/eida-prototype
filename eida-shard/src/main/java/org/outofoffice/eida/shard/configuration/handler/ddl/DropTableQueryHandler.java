package org.outofoffice.eida.shard.configuration.handler.ddl;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.QueryHandler;
import org.outofoffice.eida.shard.controller.DdlController;

@RequiredArgsConstructor
public class DropTableQueryHandler implements QueryHandler {

    private final DdlController ddlController;

    @Override
    public String handle(String parameter) {
        ddlController.dropTable(parameter);
        return null;
    }
}
