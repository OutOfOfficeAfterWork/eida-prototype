package org.outofoffice.eida.manager.core;

import org.outofoffice.eida.manager.core.controller.DllController;
import org.outofoffice.eida.manager.core.controller.DmlController;
import org.outofoffice.eida.manager.core.handler.dll.*;
import org.outofoffice.eida.manager.core.handler.QueryHandler;
import org.outofoffice.eida.manager.core.handler.dml.*;
import org.outofoffice.eida.manager.exception.HandlerNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class QueryHandlerMappings {
    private final Map<String, QueryHandler> mappings = new HashMap<>();


    public QueryHandlerMappings() {
        final DllController dllController = DllController.INSTANCE;
        final DmlController dmlController = DmlController.INSTANCE;
        // ddl

        // dll
        mappings.put("get all", new GetAllQueryHandler(dllController));
        mappings.put("get dst", new GetDestQueryHandler(dllController));
        mappings.put("get src", new GetSrcQueryHandler(dllController));
        mappings.put("report insert", new ReportInsertQueryHandler(dllController));
        mappings.put("report delete", new ReportDeleteQueryHandler(dllController));

        // dml
        mappings.put("select all", new SelectAllQueryHandler(dmlController));
        mappings.put("select", new SelectQueryHandler(dmlController));
        mappings.put("insert", new InsertQueryHandler(dmlController));
        mappings.put("update", new UpdateQueryHandler(dmlController));
        mappings.put("delete", new DeleteQueryHandler(dmlController));
    }

    public QueryHandler mustGet(String command) {
        QueryHandler queryHandler = mappings.get(command);
        if (queryHandler == null) throw new HandlerNotFoundException(command);
        return queryHandler;
    }

}
