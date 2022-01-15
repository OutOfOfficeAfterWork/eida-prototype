package org.outofoffice.eida.manager.core;

import lombok.extern.slf4j.Slf4j;
import org.outofoffice.eida.manager.core.handler.QueryHandler;


@Slf4j
public class QueryDispatcher {

    private final QueryHandlerMappings queryHandlerMappings = new QueryHandlerMappings();
    private final ExceptionHandlerMappings exceptionHandlerMappings = new ExceptionHandlerMappings();


    public String send(String request) {
        try {
            String[] s = request.split(", ");
            String command = s[0];
            String param = s[1];

            QueryHandler queryHandler = queryHandlerMappings.mustGet(command);
            return queryHandler.handle(param.split(" "));
        } catch (Exception e) {
            e.printStackTrace();
            return exceptionHandlerMappings.getOrDefault(e.getClass()).handle(e);
        }
    }

}
