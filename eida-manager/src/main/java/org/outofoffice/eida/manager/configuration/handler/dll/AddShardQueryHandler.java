package org.outofoffice.eida.manager.configuration.handler.dll;

import lombok.RequiredArgsConstructor;
import org.outofoffice.eida.common.QueryHandler;
import org.outofoffice.eida.manager.controller.DllController;

import java.util.Set;


@RequiredArgsConstructor
public class AddShardQueryHandler implements QueryHandler {

    private final DllController dllController;

    @Override
    public String handle(String parameter) {
        String[] params = parameter.split(" ");
        String url = params[0];
        dllController.addShard(url);
        return null;
    }

}
