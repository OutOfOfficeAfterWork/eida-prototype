package org.outofoffice.eida.manager.core.handler.dll;


import org.outofoffice.eida.manager.core.handler.QueryHandler;

public class ReportInsertQueryHandler implements QueryHandler {
    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ");
        String shardUrl = params[0];
        String tableName = params[1];
        String id = params[2];

        return null;
    }
}
