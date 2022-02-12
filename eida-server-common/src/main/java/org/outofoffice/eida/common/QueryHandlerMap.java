package org.outofoffice.eida.common;

import org.outofoffice.eida.common.exception.HandlerNotFoundException;

import java.util.HashMap;
import java.util.Map;

public abstract class QueryHandlerMap {

    protected final Map<String, QueryHandler> mappings = new HashMap<>();

    public abstract QueryHandlerMap configureMappings();

    public QueryHandler mustGet(String command) {
        QueryHandler queryHandler = mappings.get(command);
        if (queryHandler == null) throw new HandlerNotFoundException(command);
        return queryHandler;
    }

}
