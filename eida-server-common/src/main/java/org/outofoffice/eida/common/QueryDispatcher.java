package org.outofoffice.eida.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.outofoffice.eida.common.exception.EidaBadRequestException;


@Slf4j
@RequiredArgsConstructor
public class QueryDispatcher {

    private final QueryHandlerMap queryHandlerMap;

    public EidaResponse send(String request) {
        String[] s = request.split(", ");
        String command = s[0];
        String parameter = s[1];

        try {
            String code = "OK";
            String body = queryHandlerMap.mustGet(command).handle(parameter);
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
