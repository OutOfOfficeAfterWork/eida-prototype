package org.outofoffice.eida.manager.core.handler.dml;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.core.controller.DmlController;
import org.outofoffice.eida.manager.core.handler.QueryHandler;

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
