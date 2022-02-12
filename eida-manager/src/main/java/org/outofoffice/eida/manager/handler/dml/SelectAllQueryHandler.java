package org.outofoffice.eida.manager.handler.dml;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.controller.DmlController;
import org.outofoffice.eida.common.QueryHandler;

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
