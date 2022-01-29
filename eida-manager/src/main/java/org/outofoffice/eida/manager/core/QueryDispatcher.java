package org.outofoffice.eida.manager.core;

import lombok.extern.slf4j.Slf4j;
import org.outofoffice.eida.manager.exception.EidaBadRequestException;


@Slf4j
public class QueryDispatcher {

    private final QueryHandlerMappings queryHandlerMappings = new QueryHandlerMappings();

    public EidaResponse send(String request) {
        String[] s = request.split(", ");
        String command = s[0];
        String parameter = s[1];

        try {
            String code = "OK";
            String body = queryHandlerMappings.mustGet(command).handle(parameter);
            return EidaResponse.create(code, body);
        } catch (EidaBadRequestException e) {
            String code = e.getClass().getSimpleName();
            String body = e.getMessage();
            return EidaResponse.create(code, body);
        } catch (Exception e) {
            e.printStackTrace();
            String code = "ServerError";
            String body = e.getMessage();
            return EidaResponse.create(code, body);
        }
    }

}
