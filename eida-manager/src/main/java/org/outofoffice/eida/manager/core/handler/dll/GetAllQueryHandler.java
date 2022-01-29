package org.outofoffice.eida.manager.core.handler.dll;


import org.outofoffice.eida.manager.core.handler.QueryHandler;


public class GetAllQueryHandler implements QueryHandler {
    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ");
        String tableName = params[0];

        return "127.0.0.1:10830";
    }
}
