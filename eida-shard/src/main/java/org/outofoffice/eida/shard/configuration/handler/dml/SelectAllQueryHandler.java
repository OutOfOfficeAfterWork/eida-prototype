package org.outofoffice.eida.shard.configuration.handler.dml;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.QueryHandler;
import org.outofoffice.eida.shard.controller.DmlController;

@RequiredArgsConstructor
public class SelectAllQueryHandler implements QueryHandler {
    private final DmlController dmlController;

    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ");
        String tableName = params[0];
        return dmlController.selectAll(tableName);
    }
}
