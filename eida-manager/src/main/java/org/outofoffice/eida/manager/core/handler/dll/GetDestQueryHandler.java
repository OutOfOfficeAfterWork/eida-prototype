package org.outofoffice.eida.manager.core.handler.dll;


import org.outofoffice.eida.manager.core.handler.QueryHandler;
import org.outofoffice.eida.manager.core.io.FileFacade;

import java.util.Map;

public class GetDestQueryHandler implements QueryHandler {
    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ");
        String tableName = params[0];
        Map<String, String> map = FileFacade.readTableFile(tableName);

        return "127.0.0.1:10830";
    }
}
