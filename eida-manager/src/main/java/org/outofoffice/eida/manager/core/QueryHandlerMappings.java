package org.outofoffice.eida.manager.core;

import org.outofoffice.eida.manager.core.handler.dll.*;
import org.outofoffice.eida.manager.core.handler.QueryHandler;
import org.outofoffice.eida.manager.core.handler.dml.*;
import org.outofoffice.eida.manager.exception.HandlerNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class QueryHandlerMappings {
    private final Map<String, QueryHandler> mappings = new HashMap<>();

    public QueryHandlerMappings() {
        // ddl

        // dll
        mappings.put("get all", new GetAllQueryHandler());
        mappings.put("get dst", new GetDestQueryHandler());
        mappings.put("get src", new GetSrcQueryHandler());
        mappings.put("report insert", new ReportInsertQueryHandler());
        mappings.put("report delete", new ReportDeleteQueryHandler());

        // dml
        mappings.put("select all", new SelectAllQueryHandler());
        mappings.put("select", new SelectQueryHandler());
        mappings.put("insert", new InsertQueryHandler());
        mappings.put("update", new UpdateQueryHandler());
        mappings.put("delete", new DeleteQueryHandler());
    }

    public QueryHandler mustGet(String command) {
        QueryHandler queryHandler = mappings.get(command);
        if (queryHandler == null) throw new HandlerNotFoundException(command);
        return queryHandler;
    }

}
