package org.outofoffice.eida.manager.core.handler.dml;

import org.outofoffice.eida.manager.core.controller.DmlController;
import org.outofoffice.eida.manager.core.handler.QueryHandler;

public class SelectAllQueryHandler implements QueryHandler {

    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ");
        String tableName = params[0];
        return "table results";
    }
}
