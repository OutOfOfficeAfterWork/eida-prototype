package org.outofoffice.eida.manager.core;

import lombok.extern.slf4j.Slf4j;
import org.outofoffice.eida.manager.handler.QueryHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class QueryDispatcher {

    private final Map<String, QueryHandler> mappings = new HashMap<>();
//    private final Map<Class<? extends Exception>, ExceptionHandler> exceptionMappings = new HashMap<>();

    public String send(String request) {
        try {
            String[] s = request.split(" ");
            String command = s[0];
            String param = s[1];

            QueryHandler queryHandler = mappings.get(command);
            return queryHandler.handle(param);
        } catch (Exception e) {
            log.error(e.getMessage());
            // ExceptionHandler exceptionHandler = exceptionMappings.get(e.getClass());
            // return exceptionHandler.handle(e);
            return "500";
        }
    }

}
