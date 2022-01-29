package org.outofoffice.eida.manager.core.handler.dll;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.core.controller.DllController;
import org.outofoffice.eida.manager.core.handler.QueryHandler;

import java.util.List;


@RequiredArgsConstructor
public class GetAllQueryHandler implements QueryHandler {

    private final DllController dllController;

    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ");
        String tableName = params[0];
        List<String> allShardUrls = dllController.getAllShardUrls(tableName);
        return String.join(",", allShardUrls);
   }

}
