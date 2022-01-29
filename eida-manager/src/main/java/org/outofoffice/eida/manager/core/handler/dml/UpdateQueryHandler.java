package org.outofoffice.eida.manager.core.handler.dml;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.core.controller.DmlController;
import org.outofoffice.eida.manager.core.handler.QueryHandler;

@RequiredArgsConstructor
public class UpdateQueryHandler implements QueryHandler {
    private final DmlController dmlController;

    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ", 2);
        String tableName = params[0];
        String data = params[1];
        dmlController.update(tableName, data);

        return null;
    }
}
