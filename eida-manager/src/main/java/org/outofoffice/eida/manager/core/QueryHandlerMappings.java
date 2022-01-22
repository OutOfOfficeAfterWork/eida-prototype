package org.outofoffice.eida.manager.core;

import org.outofoffice.eida.manager.core.handler.GetDestQueryHandler;
import org.outofoffice.eida.manager.core.handler.QueryHandler;
import org.outofoffice.eida.manager.exception.HandlerNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class QueryHandlerMappings {
    private final Map<String, QueryHandler> mappings = new HashMap<>();

    public QueryHandlerMappings() {
        mappings.put("get dst", new GetDestQueryHandler());
    }

    public QueryHandler mustGet(String command) {
        QueryHandler queryHandler = mappings.get(command);
        if (queryHandler == null) throw new HandlerNotFoundException(command);
        return queryHandler;
    }

}
