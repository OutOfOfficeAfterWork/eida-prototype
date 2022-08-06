package org.outofoffice.eida.shard.configuration.handler.ddl;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.QueryHandler;
import org.outofoffice.eida.shard.controller.DdlController;

import java.util.Arrays;

@RequiredArgsConstructor
public class CreateColumnQueryHandler implements QueryHandler {

    private final DdlController ddlController;

    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ", 2);

        String tableName = params[0];

        if (params.length == 2) {
            String defaultValue = params[1];
            ddlController.createColumn(tableName, defaultValue);
        } else {
            ddlController.createColumn(tableName);
        }

        return null;
    }

}
