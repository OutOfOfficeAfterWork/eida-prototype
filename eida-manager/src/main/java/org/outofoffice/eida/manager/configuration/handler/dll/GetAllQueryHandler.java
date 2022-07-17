package org.outofoffice.eida.manager.configuration.handler.dll;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.manager.controller.DllController;
import org.outofoffice.eida.common.QueryHandler;

import java.util.Set;


@RequiredArgsConstructor
public class GetAllQueryHandler implements QueryHandler {

    private final DllController dllController;

    @Override
    public String handle(String parameter) {
        return parameter.isEmpty()
            ? allShardUrls()
            : shardUrlsAndScheme(parameter);
    }

    private String allShardUrls() {
        Set<String> allShardUrls = dllController.getAllShardUrls();
        return String.join(",", allShardUrls);
    }

    private String shardUrlsAndScheme(String parameter) {
        String[] params = parameter.split(" ");
        String tableName = params[0];
        return dllController.getSources(tableName);
    }


}
